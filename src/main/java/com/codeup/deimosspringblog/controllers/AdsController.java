package com.codeup.deimosspringblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdsController {
    @GetMapping("/ads")
    public String allAds(){
        return "index";
    }
}
