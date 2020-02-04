package com.codeup.deimosspringblog;

import com.codeup.deimosspringblog.models.Ad;
import com.codeup.deimosspringblog.models.User;
import com.codeup.deimosspringblog.repositories.AdRepository;
import com.codeup.deimosspringblog.repositories.UserRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import javax.servlet.http.HttpSession;

import static junit.framework.TestCase.assertNotNull;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.not;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class AdsIntegrationTests {

    // dependencies...

    @Autowired
    private AdRepository adsDao;

    @Autowired
    private UserRepository userDao;

    @Autowired
    private PasswordEncoder pen;

    @Autowired
    private MockMvc mvc;

    private User testUser;

    private HttpSession session;

    private void setUpTestUser() {
        this.testUser = userDao.findByUsername("testUser");
        if (testUser == null) {
            testUser = new User();
            testUser.setEmail("test@email.com");
            testUser.setPassword(pen.encode("password"));
            testUser.setUsername("testUser");
            this.testUser = userDao.save(testUser);
        }
    }

    private void setUpSession() throws Exception {
        this.session = mvc.perform(post("/login").with(csrf()).param("username", testUser.getUsername()).param("password", "password"))
                .andExpect(status().isFound())
                .andReturn()
                .getRequest()
                .getSession();
    }

    // create and login as test user
    @Before
    public void setUp() throws Exception {
        setUpTestUser();
        setUpSession();
    }

    // sanity test
    @Test
    public void testContext() {
        assertNotNull(mvc);
        assertNotNull(session);
    }

    /**
     * To test entity creation, check for a 3xx redirection
     */
    @Test
    public void testAdCreation() throws Exception {
        mvc.perform(
                post("/ads/create")
                        .with(csrf())
                        .session((MockHttpSession) session)
                        .param("title", "Test Title")
                        .param("description", "This is a Test Ad")
        )
                .andExpect(status().is3xxRedirection())
                .andDo(print());

        // clean up
        Ad a = adsDao.findByTitle("Test Title");
        adsDao.deleteById(a.getId());
    }

    /**
     * To test entity show, check for OK status and the content matching a given entity
     */
    @Test
    public void testAdShow() throws Exception {
        // select first ad to test
        Ad adToTestShow = adsDao.findAll().get(0);

        // complete and verify request to show it
        mvc.perform(
                get("/ads/" + adToTestShow.getId())
        )
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(adToTestShow.getTitle())))
                .andExpect(content().string(containsString(adToTestShow.getDescription())));
    }

    /**
     * To test entity index, check for OK status and the content matching a random entity
     */
    @Test
    public void testAdIndex() throws Exception {
        // select first ad to test
        Ad adToTestShow = adsDao.findAll().get(0);

        // complete and verify request to show it in the index
        mvc.perform(
                get("/ads")
        )
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(adToTestShow.getTitle())))
                .andExpect(content().string(containsString(adToTestShow.getDescription())));
    }

    /**
     * To test entity edit, check for redirect and updated content for show
     */
    @Test
    public void testPostEdit() throws Exception {

        // select an ad to edit and keep the original values for later clean up
        Ad updateAd = adsDao.findAll().get(0);

        String originalTitle = updateAd.getTitle();
        String originalDescription = updateAd.getDescription();

        updateAd.setTitle("Updated Title");
        updateAd.setDescription("This is the updated description!");

        // execute the update
        mvc.perform(
                post("/ads/" + updateAd.getId() + "/edit").with(csrf()).session((MockHttpSession) session)
                        .param("title", updateAd.getTitle())
                        .param("description", updateAd.getDescription())
        )
                .andExpect(status().is3xxRedirection());

        // verify the change in the ad show
        mvc.perform(get("/ads/" + updateAd.getId()))
                .andExpect(view().name("ads/show"))
                .andExpect(content().string(containsString(updateAd.getDescription())))
                .andExpect(content().string(containsString(updateAd.getTitle())));

        // clean up by setting ad back to original values
        mvc.perform(
                post("/ads/" + updateAd.getId() + "/edit").with(csrf()).session((MockHttpSession) session)
                        .param("title", originalTitle)
                        .param("description", originalDescription)
        );

    }


    /**
     * To test entity delete, check for redirect and that the index lacks content for the deleted entity
     */
    @Test
    public void testAdDelete() throws Exception {

        // create ad to destroy
        this.mvc.perform(
                post("/ads/create").with(csrf())
                        .session((MockHttpSession) session)
                        .param("title", "Delete Title")
                        .param("description", "Ad to delete")
        )
                .andExpect(status().is3xxRedirection());

        // get newly created ad
        Ad adToDelete = adsDao.findByTitle("Delete Title");

        // attempt delete of ad
        mvc.perform(
                post("/ads/" + adToDelete.getId() + "/delete").with(csrf()).session((MockHttpSession) session)
        )
                .andExpect(status().is3xxRedirection());

        // verify that it does not exist in the index of ads
        mvc.perform(
                get("/ads").session((MockHttpSession) session)
        )
                .andExpect(status().isOk())
                .andExpect(content().string(not(containsString("Test Title"))));
    }

    // remove test user
    @After
    public void cleanUp() {
        userDao.delete(testUser);
    }


}
