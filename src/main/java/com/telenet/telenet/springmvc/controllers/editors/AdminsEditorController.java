package com.telenet.telenet.springmvc.controllers.editors;

import com.telenet.telenet.utils.database.UserADMINDataBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.SQLException;

@Controller
public class AdminsEditorController {
    @Autowired
    UserADMINDataBase userADMINDataBase;
    @GetMapping("/adminsEditor")
    public String mainPage(Model model, @RequestParam(name = "login") String login) throws SQLException {

        model.addAttribute("value", userADMINDataBase.loginSearch(login).get(0));
        return "modelsLists/adminsEditor";
    }
    @PostMapping("/adminsEditor")
    public String userUpdate(@RequestParam(name = "prevLogin") String prevLogin, @RequestParam(name = "name") String newName,@RequestParam(name = "login") String newLogin, @RequestParam(name = "password") String newPassword) throws SQLException {
//        storage.getAdminByLogin(prevLogin).setName(newName);
//        storage.getAdminByLogin(prevLogin).setPassword(newPassword);
//        storage.getAdminByLogin(prevLogin).setLogin(newLogin);
        userADMINDataBase.updateByLogin(prevLogin,newLogin,newName,newPassword);
        return "redirect:/admin/admins";
    }

}
