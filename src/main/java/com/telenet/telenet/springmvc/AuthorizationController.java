package com.telenet.telenet.springmvc;

import com.telenet.telenet.models.user.User;
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
        model.addAttribute("error","" );
        return "authorization/authorization";
    }

    @PostMapping("/authorization")
    public String checker(Model model, @RequestParam(name = "login") String login, @RequestParam(name = "password") String password ){
        User user = storage.getAdminByLogin(login);
        User userimpl = storage.getUserImplByLogin(login);
        if(user != null){
            if(user.getPassword().equals(password)) {
                return "redirect:/admin";
            }
            else {
                return "authorization/authorization";
            }
        }
        else if (userimpl != null){
            if (userimpl.getPassword().equals(password)) {
                return "redirect:/mainPage?login="+userimpl.getLogin();
            }
            else {
                return "authorization/authorization";
            }
        }
        else{
            model.addAttribute("error","error" );
            model.addAttribute("login",login);
            return "authorization/authorization";
        }
    }

}
