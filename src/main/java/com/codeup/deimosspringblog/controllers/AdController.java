package com.codeup.deimosspringblog.controllers;

import com.codeup.deimosspringblog.models.Ad;
import com.codeup.deimosspringblog.repositories.AdRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class AdController {

    // Dependency Injection
    private AdRepository adsDao;

    public AdController(AdRepository adsDao) {
        this.adsDao = adsDao;
    }

    @GetMapping("/ads")
    public String allAds() {
        return "index";
    }

    @GetMapping("/ads/jpa")
    @ResponseBody
    public List<Ad> returnAds() {
        return adsDao.findAll();
    }

    @GetMapping("/ads/jpa/create")
    public void createAd() {
        Ad ad = new Ad();
        ad.setTitle("A new ad!");
        ad.setDescription("This describes the new ad!");
        adsDao.save(ad);
    }

    @GetMapping("/ads/order")
    @ResponseBody
    public List<Ad> returnAdsByTitle() {
        return adsDao.findByOrderByTitle();
    }

    @GetMapping("/ads/search")
    @ResponseBody
    public Ad returnAdByTitle() {
        return adsDao.findByTitle("B");
    }


}
