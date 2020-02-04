package com.codeup.deimosspringblog;

import com.codeup.deimosspringblog.models.User;
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

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertNotNull;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class AboutPageIntegrationTests {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private UserRepository userDao;

    @Autowired
    private PasswordEncoder pen;

    private HttpSession session;

    private User testUser;

    @Before
    public void setUp() throws Exception {
        // create a test user
        testUser = userDao.findByUsername("testUser");
        if (testUser == null) {
            testUser = userDao.save(new User("testUser", "test@email.com", pen.encode("password")));
        }

        // log in
        this.session = mvc.perform(
                post("/login").with(csrf())
                        .param("username", "testUser")
                        .param("password", "password")
                )
                .andExpect(status().is3xxRedirection())
                .andReturn()
                .getRequest()
                .getSession();
    }

    @Test
    public void testUserObject() {
        assertNotNull(testUser);
    }

    @Test
    public void testSessionNotNull() {
        assertNotNull(session);
    }

    // sanity test
    @Test
    public void testMvcNotNull() {
        assertNotNull(mvc);
    }

    @Test
    public void testAboutPage() throws Exception {
        mvc.perform(get("/about").session((MockHttpSession) session))
        .andExpect((status().isOk()))
        .andExpect(view().name("about"))
        .andExpect(content().string(containsString("About")));
    }

    @After
    public void cleanUp() {
        userDao.delete(testUser);
    }

}
