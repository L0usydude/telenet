package com.telenet.telenet.springmvc.controllers.editors;


import com.telenet.telenet.models.enums.status.StatusEnum;
import com.telenet.telenet.utils.database.OrderDataBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.SQLException;

@Controller
public class OrdersEditorController {
    @Autowired
    OrderDataBase orderDataBase;
    @GetMapping("/ordersEditor")
    public String mainPage(Model model, @RequestParam(name = "id") int id) throws SQLException {
        model.addAttribute("value", orderDataBase.getOrder(id));
        model.addAttribute("statusENUM", StatusEnum.values());
        return "modelsLists/ordersEditor";
    }
    @PostMapping("/ordersEditor")
    public String userUpdate(@RequestParam(name = "id") String id, @RequestParam(name = "userId") String newUserId,@RequestParam(name = "serviceId") String newServiceId,
                             @RequestParam(name = "status") String newStatus,@RequestParam(name = "action") String newAction) throws SQLException {
        orderDataBase.updateById(id, newUserId, newServiceId, newStatus, newAction);
        return "redirect:/admin/orders";
    }
}
