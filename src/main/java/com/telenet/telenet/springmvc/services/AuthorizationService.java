package com.telenet.telenet.springmvc.services;

import com.telenet.telenet.models.user.User;
import com.telenet.telenet.utils.database.UserADMINDataBase;
import com.telenet.telenet.utils.database.UserDEFAULT_USERDataBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.SQLException;

@Service
public class AuthorizationService {
    @Autowired
    UserDEFAULT_USERDataBase userDEFAULT_userDataBase;
    @Autowired
    UserADMINDataBase userADMINDataBase;
    public String checker(Model model, @RequestParam(name = "login") String login, @RequestParam(name = "password") String password ) throws SQLException {
        User user = userADMINDataBase.getByLogin(login);
        User userimpl = userDEFAULT_userDataBase.getByLogin(login);
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
