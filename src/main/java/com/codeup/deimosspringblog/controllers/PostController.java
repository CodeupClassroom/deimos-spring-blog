package com.codeup.deimosspringblog.controllers;

import com.codeup.deimosspringblog.models.Post;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;

@Controller
public class PostController {

    @GetMapping("/posts")

    public String postsIndex(Model model){
        ArrayList<Post> postList = new ArrayList<Post>();
        postList.add(new Post(2, "Title 2", "Description 2"));
        postList.add(new Post(3, "Title 3", "Description 3"));

        model.addAttribute("posts", postList);

        return "posts/index";
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

}
