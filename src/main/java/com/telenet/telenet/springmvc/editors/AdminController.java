package com.telenet.telenet.springmvc.editors;

import com.telenet.telenet.utils.Storage;
import com.telenet.telenet.utils.database.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.sql.SQLException;

@Controller @RequestMapping("/admin")
public class AdminController {
    @Autowired
    UserADMINBase userADMINBase;
    @Autowired
    UserDEFAULT_USERBase userDEFAULT_userBase;
    @Autowired
    AreaBase areaBase;
    @Autowired
    TemplateBase templateBase;
    @Autowired
    ServiceBase serviceBase;
    @Autowired
    OrderBase orderBase;

    @GetMapping
    public String startAdmin(){
        return "users/admin";
    }
    @GetMapping("/admins")
    public String adminsOut(Model model) throws SQLException {
        if(!model.containsAttribute("adminsCollection")){
            model.addAttribute("adminsCollection", userADMINBase.getListOfUsers());
        }
        return "modelsLists/admins";
    }
    @GetMapping("/users")
    public String usersOut(Model model) throws SQLException {
        if (!model.containsAttribute("usersCollection")){
            model.addAttribute("usersCollection", userDEFAULT_userBase.getListOfUsers());
        }
        return "modelsLists/users";
    }
    @GetMapping("/templates")
    public String templatesOut(Model model) throws SQLException {
        if (!model.containsAttribute("templatesCollection")){
            model.addAttribute("templatesCollection", templateBase.getListOfTemplates());
        }
        return "modelsLists/templates";
    }
    @GetMapping("/services")
    public String servicesOut(Model model) throws SQLException {
        if (!model.containsAttribute("servicesCollection")) {
            model.addAttribute("servicesCollection", serviceBase.getListOfServices());
        }
        return "modelsLists/services";
    }
    @GetMapping("/orders")
    public String ordersOut(Model model) throws SQLException {
        if (!model.containsAttribute("ordersCollection")) {
            model.addAttribute("ordersCollection", orderBase.getListOfOrders());}
        return "modelsLists/orders";
    }
    @GetMapping("/areas")
    public String areasOut(Model model) throws SQLException {
        if(!model.containsAttribute("areasCollection")){
            model.addAttribute("areasCollection", areaBase.getListOfAreas());
        }
        return "modelsLists/areas";
    }

    @PostMapping("/adminLoginSearch")
    public String adminLoginSearch(RedirectAttributes attributes, @RequestParam(name = "search") String search) throws SQLException {

        attributes.addFlashAttribute("adminsCollection",userADMINBase.loginSearch(search));
        return "redirect:/admin/admins";
    }

    @PostMapping("/userLoginSearch")
    public String userLoginSearch(RedirectAttributes attributes, @RequestParam(name = "search") String search) throws SQLException {
        attributes.addFlashAttribute("usersCollection",userDEFAULT_userBase.loginSearch(search));
        return "redirect:/admin/users";
    }

    @PostMapping("/areaNameSearch")
    public String areaNameSearch(RedirectAttributes attributes, @RequestParam(name = "search") String search) throws SQLException {
        attributes.addFlashAttribute("areasCollection", areaBase.nameSearch(search));
        return "redirect:/admin/areas";
    }

    @PostMapping("/templateNameSearch")
    public String templateNameSearch(RedirectAttributes attributes, @RequestParam(name = "search") String search) throws SQLException {
        attributes.addFlashAttribute("templatesCollection", templateBase.nameSearch(search));
        return "redirect:/admin/templates";
    }

    @PostMapping("/serviceNameSearch")
    public String serviceNameSearch(RedirectAttributes attributes, @RequestParam(name = "search") String search) throws SQLException {
        attributes.addFlashAttribute("servicesCollection", serviceBase.nameSearch(search));
        return "redirect:/admin/services";
    }

    @PostMapping("/OrderUserIdSearch")
    public String OrderUserIdSearch(RedirectAttributes attributes, @RequestParam(name = "search") String search) throws SQLException {
        attributes.addFlashAttribute("ordersCollection", orderBase.OrderUserIdSearch(search));
        return "redirect:/admin/orders";
    }
}
