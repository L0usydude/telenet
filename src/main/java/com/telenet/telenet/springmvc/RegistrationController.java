package com.telenet.telenet.springmvc;

import com.telenet.telenet.models.enums.roles.RoleEnum;
import com.telenet.telenet.models.user.User;
import com.telenet.telenet.utils.Storage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RegistrationController {
    @Autowired
    Storage storage;
    @Autowired
    public RegistrationController(Storage storage1){
        storage = storage1;
    }
    @GetMapping("/registration")
    public String authorization(Model model){
        model.addAttribute("error","" );
        return "authorization/registration";
    }

    @PostMapping("/registration")
    public String checker(Model model, @RequestParam(name = "login") String login, @RequestParam(name = "password") String password ){
        if (storage.getUserImplByLogin(login) == null && storage.getAdminByLogin(login) == null) {
            User newUser = new User(123,"name1",login,password, RoleEnum.DEFAULT_USER);
            storage.addUserImpl(newUser);
            return "authorization/authorization";
        }
        else {
            return "authorization/registration";
        }
    }
}
