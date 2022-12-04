package com.telenet.telenet.springmvc.editors;

import com.telenet.telenet.models.user.User;
import com.telenet.telenet.utils.Storage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UsersEditorController {
    @Autowired
    Storage storage;
    public UsersEditorController(Storage storage1){
        storage = storage1;
    }
    @GetMapping("/usersEditor")
    public String mainPage(Model model, @RequestParam(name = "login") String login){
        model.addAttribute("value",storage.getUserImplByLogin(login));
        return "modelsLists/usersEditor";
    }
    @PostMapping("/usersEditor")
    public String userUpdate(@RequestParam(name = "prevLogin") String prevLogin, @RequestParam(name = "name") String newName,@RequestParam(name = "login") String newLogin, @RequestParam(name = "password") String newPassword){
        storage.getUserImplByLogin(prevLogin).setName(newName);
        storage.getUserImplByLogin(prevLogin).setPassword(newPassword);
        storage.getUserImplByLogin(prevLogin).setLogin(newLogin);
        return "redirect:/admin/users";
    }


}
