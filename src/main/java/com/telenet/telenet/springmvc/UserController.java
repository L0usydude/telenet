package com.telenet.telenet.springmvc;

import com.telenet.telenet.utils.Storage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller @RequestMapping("/user")
public class UserController {
    @Autowired
    Storage storage;
    public UserController(Storage storage1){storage = storage1;};
    @GetMapping
    public String startUser(Model model, @RequestParam(name = "login") String login){
        model.addAttribute("login", login);
        return "users/user";
    }

    @GetMapping("/userServices")
    public String userTemplatesOut(Model model, @RequestParam(name = "login") String login){
        model.addAttribute("userServicesCollection", storage.getServiceListByUserImplLogin(login));

        return "modelsLists/userServices?login=";
    }

}