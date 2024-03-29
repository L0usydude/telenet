package com.telenet.telenet.springmvc.controllers.editors;

import com.telenet.telenet.utils.database.UserDEFAULT_USERDataBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.SQLException;

@Controller
public class UsersEditorController {
    @Autowired
    UserDEFAULT_USERDataBase userDEFAULT_userDataBase;
    @GetMapping("/usersEditor")
    public String mainPage(Model model, @RequestParam(name = "login") String login) throws SQLException {
        model.addAttribute("value", userDEFAULT_userDataBase.loginSearch(login).get(0));
        return "modelsLists/usersEditor";
    }
    @PostMapping("/usersEditor")
    public String userUpdate(@RequestParam(name = "prevLogin") String prevLogin, @RequestParam(name = "name") String newName,@RequestParam(name = "login") String newLogin, @RequestParam(name = "password") String newPassword) throws SQLException {
        userDEFAULT_userDataBase.updateByLogin(prevLogin,newLogin,newName,newPassword);
        return "redirect:/admin/users";
    }


}
