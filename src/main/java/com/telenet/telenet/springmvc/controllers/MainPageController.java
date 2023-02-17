package com.telenet.telenet.springmvc.controllers;

import com.telenet.telenet.utils.Storage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainPageController {

    @GetMapping("/mainPage")
    public String mainPage(Model model, @RequestParam(name = "login") String login){
        model.addAttribute("login", login);
        return "/mainPage";
    }
    @GetMapping("/infoPage")
    public String infoPageOut(Model model, @RequestParam(name = "login") String login){
        model.addAttribute("login", login);
        System.out.println(login);
        return "/infoPage";
    }

}
