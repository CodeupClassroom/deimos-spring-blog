package com.codeup.deimosspringblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("/hello")
    @ResponseBody
    public String hello(){
        return "Hello World!";
    }

    @GetMapping("/hello/{cohort}")
    @ResponseBody
    public String hello(@PathVariable String cohort){
        return "Hello " + cohort;
    }
}
