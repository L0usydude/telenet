package com.telenet.telenet.springmvc.controllers;

import com.telenet.telenet.springmvc.services.RegistrationService;
import com.telenet.telenet.utils.database.UserADMINDataBase;
import com.telenet.telenet.utils.database.UserDEFAULT_USERDataBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.SQLException;

@Controller
public class RegistrationController {
    @Autowired
    UserADMINDataBase userADMINDataBase;
    @Autowired
    UserDEFAULT_USERDataBase userDEFAULT_userDataBase;
    @Autowired
    RegistrationService registrationService;

    @GetMapping("/registration")
    public String registration(Model model){
        model.addAttribute("error","" );
        return "authorization/registration";
    }

    @PostMapping("/registration")
    public String checker(Model model, @RequestParam(name = "login") String login, @RequestParam(name = "password") String password ) throws SQLException {
        return registrationService.checker(model, login, password);
    }
}
