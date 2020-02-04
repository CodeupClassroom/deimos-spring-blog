package com.codeup.deimosspringblog.controllers;

import com.codeup.deimosspringblog.models.Ad;
import com.codeup.deimosspringblog.models.User;
import com.codeup.deimosspringblog.repositories.AdRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AdController {

    private AdRepository adsDao;

    public AdController(AdRepository adsDao) {
        this.adsDao = adsDao;
    }

    @GetMapping("/ads")
    public String index(Model model) {
        model.addAttribute("ads", adsDao.findAll());
        return "ads/index";
    }

    @GetMapping("/ads/{id}")
    public String show(Model model, @PathVariable long id) {
        model.addAttribute("ad", adsDao.getOne(id));
        return "ads/show";
    }

    @GetMapping("/ads/create")
    public String create(Model model) {
        model.addAttribute("ad", new Ad());
        return "ads/create";
    }

    @PostMapping("/ads")
    public String insert(@ModelAttribute Ad ad) {
        ad.setUser((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        adsDao.save(ad);
        return "redirect:/ads";
    }

    @GetMapping("/ads/{id}/edit")
    public String edit(@PathVariable long id, Model model) {
        model.addAttribute("ad", adsDao.getOne(id));
        return "ads/edit";
    }

    @PostMapping("/ads/{id}/edit")
    public String update(@PathVariable long id, @ModelAttribute Ad ad) {
        Ad oldAd = adsDao.getOne(id);
        oldAd.setTitle(ad.getTitle());
        oldAd.setDescription(ad.getDescription());
        adsDao.save(oldAd);
        return "redirect:/ads/" + id;
    }

    @PostMapping("/ads/{id}/delete")
    public String delete(@PathVariable long id) {
        adsDao.deleteById(id);
        return "redirect:/ads";
    }

}
