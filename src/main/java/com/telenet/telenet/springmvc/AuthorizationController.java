package com.telenet.telenet.springmvc;

import com.telenet.telenet.utils.Storage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthorizationController {
    @Autowired
    Storage storage;
    @Autowired
    public AuthorizationController(Storage storage1){
        storage = storage1;
    }
    @GetMapping("/authorization")
    public String authorization(Model model){
        model.addAttribute("error","" );
        return "authorization";
    }

    @PostMapping("/authorization")
    public String checker(Model model, @RequestParam(name = "login") String login, @RequestParam(name = "password") String password ){
        if(storage.getAdminByLogin(login) != null){
            return "redirect:/admin/admins";
        }
        else if (storage.getUserImplByLogin(login) != null){
            return "redirect:/test";
        }
        else{
            model.addAttribute("error","error" );
            model.addAttribute("login",login);
            return "/authorization";
        }
    }
}
