package com.telenet.telenet.springmvc;

import com.telenet.telenet.utils.Storage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller @RequestMapping("/admin")
public class AdminController {
    @Autowired
    Storage storage1;
    @GetMapping
    public String startAdmin(){
        return "users/admin";
    }
    @GetMapping("/admins")
    public String adminsOut(Model model){
        model.addAttribute("adminsCollection", storage1.getUserAdminMap().values());
        return "modelsLists/admins";
    }
    @GetMapping("/users")
    public String usersOut(Model model){
        model.addAttribute("usersCollection", storage1.getUserImplMap().values());
        return "modelsLists/users";
    }
    @GetMapping("/templates")
    public String templatesOut(Model model){
        model.addAttribute("templatesCollection", storage1.getTemplateMap().values());
        return "modelsLists/templates";
    }
    @GetMapping("/services")
    public String servicesOut(Model model){
        model.addAttribute("servicesCollection", storage1.getServiceMap().values());
        return "modelsLists/services";
    }
    @GetMapping("/orders")
    public String ordersOut(Model model)
    {
        model.addAttribute("ordersCollection", storage1.getOrderMap().values());
        return "modelsLists/orders";
    }
    @GetMapping("/areas")
    public String areasOut(Model model)
    {
        model.addAttribute("areasCollection", storage1.getAreaMap().values());
        return "modelsLists/areas";
    }
}
