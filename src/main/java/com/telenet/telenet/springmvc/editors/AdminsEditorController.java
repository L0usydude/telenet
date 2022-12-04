package com.telenet.telenet.springmvc.editors;

import com.telenet.telenet.utils.Storage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AdminsEditorController {
    @Autowired
    Storage storage;
    public AdminsEditorController(Storage storage1){
        storage = storage1;
    }
    @GetMapping("/adminsEditor")
    public String mainPage(Model model, @RequestParam(name = "login") String login){
        model.addAttribute("value",storage.getAdminByLogin(login));
        return "modelsLists/adminsEditor";
    }
    @PostMapping("/adminsEditor")
    public String userUpdate(@RequestParam(name = "prevLogin") String prevLogin, @RequestParam(name = "name") String newName,@RequestParam(name = "login") String newLogin, @RequestParam(name = "password") String newPassword){
        storage.getAdminByLogin(prevLogin).setName(newName);
        storage.getAdminByLogin(prevLogin).setPassword(newPassword);
        storage.getAdminByLogin(prevLogin).setLogin(newLogin);
        return "redirect:/admin/admins";
    }
}
