package com.codeup.deimosspringblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String loginForm(){
        return "login";
    }

    @PostMapping("/login")
    public String loginUser(@RequestParam String username,
                            @RequestParam String password, Model model){

        ArrayList<String> shoppingCart = new ArrayList<>();
        shoppingCart.add("Apples");
        shoppingCart.add("Oranges");
        shoppingCart.add("Bananas");

        boolean isAdmin = true;

        model.addAttribute("username", username);
        model.addAttribute("password", password);
        model.addAttribute("shoppingCart", shoppingCart);
        model.addAttribute("isAdmin", isAdmin);
        return "profile";

    }
}
