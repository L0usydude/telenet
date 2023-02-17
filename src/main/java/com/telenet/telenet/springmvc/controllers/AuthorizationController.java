package com.telenet.telenet.springmvc.controllers;

import com.telenet.telenet.springmvc.services.AuthorizationService;
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
public class AuthorizationController {
    @Autowired
    UserDEFAULT_USERDataBase userDEFAULT_userDataBase;
    @Autowired
    UserADMINDataBase userADMINDataBase;
    @Autowired
    AuthorizationService authorizationService;
    @GetMapping("/authorization")
    public String authorization(Model model){
        model.addAttribute("error","" );
        return "authorization/authorization";
    }

    @PostMapping("/authorization")
    public String checker(Model model, @RequestParam(name = "login") String login, @RequestParam(name = "password") String password ) throws SQLException {
        return authorizationService.checker(model,login,password);
    }

}
