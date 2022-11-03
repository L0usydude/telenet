package com.telenet.telenet.springmvc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller @RequestMapping("/admin")
public class AdminController {
    @GetMapping("/admins")
    public String adminsOut(Model model){
        model.addAttribute("adminsCollection", "admin");
        return "/admins";
    }
    @GetMapping("/users")
    public String usersOut(Model model){
        model.addAttribute("usersCollection", "user");
        return "/users";
    }
    @GetMapping("/templates")
    public String templatesOut(Model model){
        model.addAttribute("templatesCollection", "template");
        return "/templates";
    }
    @GetMapping("/services")
    public String servicesOut(Model model){
        model.addAttribute("servicesCollection", "service");
        return "/services";
    }
    @GetMapping("/orders")
    public String ordersOut(Model model)
    {
        model.addAttribute("ordersCollection", "order");
        return "orders";
    }
}
