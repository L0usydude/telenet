package com.telenet.telenet.springmvc;

import com.telenet.telenet.utils.Storage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller @RequestMapping("/user")
public class UserController {
    @Autowired
    Storage storage;
    public UserController(Storage storage1){storage = storage1;};
    @GetMapping
    public String startUser(Model model, @RequestParam(name = "login") String login){
        model.addAttribute("login", login);
        return "users/user";
    }

    @GetMapping("/userActiveServices")
    public String userServicesOut(Model model, @RequestParam(name = "login") String login){
        model.addAttribute("userActiveServicesCollection", storage.getActiveServiceListByUserImplLogin(login));

        return "modelsLists/userActiveServices";
    }


    @GetMapping("/userOrders")
    public String userOrdersOut(Model model, @RequestParam(name = "login") String login){
        model.addAttribute("userOrdersCollection", storage.getOrderListByUserImplLogin(login));
        model.addAttribute("login", login);
        return "modelsLists/userOrders";
    }

    @PostMapping("/userOrders")
    public String userOrdersDel(Model model, @RequestParam(name = "id") String id, @RequestParam(name = "login") String login)
    {
        storage.delOrder(Integer.parseInt(id));
        model.addAttribute("login",login);
        return "redirect:/mainPage?login="+login.toString();
    }

}