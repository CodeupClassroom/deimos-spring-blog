package com.codeup.deimosspringblog.controllers;

import com.codeup.deimosspringblog.repositories.PostRepository;
import com.codeup.deimosspringblog.services.EmailService;
import com.codeup.deimosspringblog.services.SillySvc;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ServiceTestController {

    private SillySvc sillySvc;
    private EmailService emailService;
    private PostRepository postDao;

    public ServiceTestController(SillySvc sillySvc, EmailService emailService, PostRepository postDao) {
        this.sillySvc = sillySvc;
        this.emailService = emailService;
        this.postDao = postDao;
    }

    @GetMapping("/silly")
    @ResponseBody
    public String returnSillyMessage() {
        return sillySvc.sayHi();
    }


    @GetMapping("/email")
    @ResponseBody
    public String sendEmail() {
        emailService.prepareAndSend(postDao.getOne(1L), "Email Subject!", "An email has been sent and here it is!!!!");
        return "Sending email...";
    }

}
