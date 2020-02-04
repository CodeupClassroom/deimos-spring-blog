package com.codeup.deimosspringblog.controllers;

import com.codeup.deimosspringblog.models.Ad;
import com.codeup.deimosspringblog.models.Post;
import com.codeup.deimosspringblog.models.User;
import com.codeup.deimosspringblog.repositories.PostRepository;
import com.codeup.deimosspringblog.repositories.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
public class PostController {

    private PostRepository postDao;
    private UserRepository userDao;

    public PostController(PostRepository postDao, UserRepository userDao) {
        this.postDao = postDao;
        this.userDao = userDao;
    }

    @GetMapping("/posts")
    public String postsIndex(Model model){
        model.addAttribute("posts", postDao.findAll());
        return "posts/index";
    }

    @GetMapping("/posts/{id}/edit")
    public String viewEditPostForm(@PathVariable long id, Model model) {
        model.addAttribute("post", postDao.getOne(id));
        return "posts/edit";
    }

    @PostMapping("/posts/{id}/edit")
    public String updatePost(@PathVariable long id, @ModelAttribute Post post) {
        User user = userDao.getOne(1L);
        post.setUser(user);
        postDao.save(post);
        return "redirect:/posts";
    }

    @PostMapping("/posts/{id}/delete")
    public String deletePost(@PathVariable long id) {
        System.out.println("Does this run?");
        postDao.deleteById(id);
        return "redirect:/posts";
    }

    @GetMapping("/posts/{id}")
    public String viewPost(@PathVariable long id, Model model){
        model.addAttribute("post", postDao.getOne(id));
        return "posts/show";
    }

    @GetMapping("/posts/create")
    public String createPostForm(Model model){
        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println(loggedInUser.getUsername());
        model.addAttribute("post", new Post());
        return "posts/create";
    }

    @PostMapping("/posts/create")
    public String submitPost(
            @ModelAttribute Post post
    ){
        User user = userDao.getOne(1L);
        post.setUser(user);
        postDao.save(post);

        return "redirect:/posts";
    }

}
