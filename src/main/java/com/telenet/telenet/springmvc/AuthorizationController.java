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
    Storage storage;
    @Autowired
    public AuthorizationController(Storage storage1){
        storage = storage1;
    }
    @GetMapping("/authorization")
    public String authorization(Model model){
        return "authorization";
    }

    @PostMapping("/authorization")
    public void checker(Model model, @RequestParam(name = "login") String login, @RequestParam(name = "password") String password ){
        System.out.println(login + password);
    }
}
