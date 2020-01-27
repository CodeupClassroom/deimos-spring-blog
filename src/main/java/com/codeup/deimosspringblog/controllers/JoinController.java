package com.codeup.deimosspringblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class JoinController {

    @GetMapping("/join")
    public String joinForm(){
        return "join";
    }

    @PostMapping("/join")
    public String joinCohort(@RequestParam String cohort, Model model){
        model.addAttribute("cohort", cohort);
        return "join";
    }

}
