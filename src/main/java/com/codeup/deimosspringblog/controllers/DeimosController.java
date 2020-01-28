package com.codeup.deimosspringblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DeimosController {

//    @GetMapping("/deimos")
//    public String countDown(){
//        return "countdown";
//    }
    @GetMapping("/deimos/{days}")

    public String countDown(@PathVariable String days, Model model){
        model.addAttribute("days", days);
        return "countdown";
    }


}
