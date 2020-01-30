package com.codeup.deimosspringblog.controllers;

import com.codeup.deimosspringblog.models.Ad;
import com.codeup.deimosspringblog.models.Post;
import com.codeup.deimosspringblog.repositories.PostRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
public class PostController {

    private PostRepository postDao;

    public PostController(PostRepository postDao) {
        this.postDao = postDao;
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
    public String updatePost(@PathVariable long id, @RequestParam String title, @RequestParam String body) {
        Post p = new Post(
            id,
            title,
            body
        );
        postDao.save(p);
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
        Post post1 = new Post(id,"Title 1", "Description 1");
        model.addAttribute("title", post1.getTitle());
        model.addAttribute("body",post1.getBody());
//        model.addAttribute("post", post1);
        return "posts/show";
    }

    @GetMapping("/posts/create")
    @ResponseBody
    public String createPostForm(){
        return "Displaying Create Post Form...";
    }

    @PostMapping("/posts/create")
    @ResponseBody
    public String submitPost(){
        return "Creating a new post...";
    }

    @GetMapping("/one/test")
    public String returnOneToOneView(Model model) {
        model.addAttribute("posts", postDao.findAll());
        return "one-to-one-test";
    }



}
