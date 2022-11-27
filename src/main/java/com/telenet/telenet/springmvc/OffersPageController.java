package com.telenet.telenet.springmvc;

import com.telenet.telenet.utils.Storage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class OffersPageController {
    @Autowired
    Storage storage;
    public OffersPageController(Storage storage1){
        storage = storage1;
    }
    @GetMapping("/offersPage")
    public String offersPage(Model model, @RequestParam(name = "login") String login){
        model.addAttribute("login", login);
        model.addAttribute("offersCollection", storage.getServiceMap().values());
        return "/offersPage";
    }
}
