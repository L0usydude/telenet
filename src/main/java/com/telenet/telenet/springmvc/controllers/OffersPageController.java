package com.telenet.telenet.springmvc.controllers;

import com.telenet.telenet.models.enums.action.ActionEnum;
import com.telenet.telenet.models.enums.status.StatusEnum;
import com.telenet.telenet.models.order.Order;
import com.telenet.telenet.utils.database.OrderDataBase;
import com.telenet.telenet.utils.database.ServiceDataBase;
import com.telenet.telenet.utils.database.UserDEFAULT_USERDataBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.SQLException;

@Controller
public class OffersPageController {
    @Autowired
    OrderDataBase orderDataBase;
    @Autowired
    UserDEFAULT_USERDataBase userDEFAULT_userDataBase;
    @Autowired
    ServiceDataBase serviceDataBase;
    @GetMapping("/offersPage")
    public String offersPage(Model model, @RequestParam(name = "login") String login) throws SQLException {
        model.addAttribute("login", login);
        model.addAttribute("offersCollection", serviceDataBase.getListOfServices());
        return "/offersPage";
    }
    @PostMapping("/offersPage")
    public String addOffer(Model model, @RequestParam(name = "serviceId") String serviceId, @RequestParam(name = "login") String login) throws SQLException {
        orderDataBase.addOrder(new Order(orderDataBase.getListOfOrders().size() + 1, userDEFAULT_userDataBase.getByLogin(login), serviceDataBase.getService(Integer.parseInt(serviceId)), StatusEnum.ENTERING, ActionEnum.CONNECT));
        model.addAttribute("login", login);
        return "redirect:/mainPage?login=" + login;
    }
}
