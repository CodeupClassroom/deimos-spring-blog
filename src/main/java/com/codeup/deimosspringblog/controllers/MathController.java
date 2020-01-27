package com.codeup.deimosspringblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MathController {

    @GetMapping("/add/{x}/and/{y}")
    @ResponseBody
    public int add(@PathVariable int x, @PathVariable int y){
        return x+y;
    }

    @GetMapping("/subtract/{x}/from/{y}")
    @ResponseBody
    public int subtract(@PathVariable int x, @PathVariable int y){
        return y-x;
    }

    @GetMapping("/multiply/{x}/and/{y}")
    @ResponseBody
    public int multiply(@PathVariable int x, @PathVariable int y){
        return x*y;
    }

    @GetMapping("/divide/{x}/by/{y}")
    @ResponseBody
    public int divide(@PathVariable int x, @PathVariable int y){
        return x/y;
    }
}
