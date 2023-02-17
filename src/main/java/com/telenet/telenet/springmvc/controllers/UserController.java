package com.telenet.telenet.springmvc.controllers;

import com.telenet.telenet.utils.database.OrderDataBase;
import com.telenet.telenet.utils.database.ServiceDataBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.SQLException;

@Controller @RequestMapping("/user")
public class UserController {
    @Autowired
    ServiceDataBase serviceDataBase;
    @Autowired
    OrderDataBase orderDataBase;
    @GetMapping
    public String startUser(Model model, @RequestParam(name = "login") String login){
        model.addAttribute("login", login);
        return "users/user";
    }

    @GetMapping("/userActiveServices")
    public String userServicesOut(Model model, @RequestParam(name = "login") String login) throws SQLException {
        model.addAttribute("userActiveServicesCollection", serviceDataBase.getActiveServiceListByUserImplLogin(login));
        return "modelsLists/userActiveServices";
    }


    @GetMapping("/userOrders")
    public String userOrdersOut(Model model, @RequestParam(name = "login") String login) throws SQLException {
        model.addAttribute("userOrdersCollection", orderDataBase.getOrderListByUserImplLogin(login));
        model.addAttribute("login", login);
        return "modelsLists/userOrders";
    }

    @PostMapping("/userOrders")
    public String userOrdersDel(Model model, @RequestParam(name = "id") String id, @RequestParam(name = "login") String login) throws SQLException {
        orderDataBase.delOrder(Integer.parseInt(id));
        model.addAttribute("login",login);
        return "redirect:/mainPage?login="+login.toString();
    }

}