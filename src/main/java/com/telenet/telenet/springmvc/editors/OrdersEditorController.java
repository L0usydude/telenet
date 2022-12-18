package com.telenet.telenet.springmvc.editors;


import com.telenet.telenet.models.enums.action.ActionEnum;
import com.telenet.telenet.models.enums.status.StatusEnum;
import com.telenet.telenet.utils.Storage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class OrdersEditorController {
    @Autowired
    Storage storage;
    public OrdersEditorController(Storage storage1){
        storage = storage1;
    }
    @GetMapping("/ordersEditor")
    public String mainPage(Model model, @RequestParam(name = "id") int id){
        model.addAttribute("value",storage.getOrder(id));
        model.addAttribute("statusENUM", StatusEnum.values());
        return "modelsLists/ordersEditor";
    }
    @PostMapping("/ordersEditor")
    public String userUpdate(@RequestParam(name = "id") String id, @RequestParam(name = "userId") String newUserId,@RequestParam(name = "serviceId") String newServiceId,
                             @RequestParam(name = "status") String newStatus,@RequestParam(name = "action") String newAction){
        storage.getOrder(Integer.parseInt(id)).setStatus(StatusEnum.valueOf(newStatus));
        storage.getOrder(Integer.parseInt(id)).setAction(ActionEnum.valueOf(newAction));
        storage.getOrder(Integer.parseInt(id)).setUser(storage.getUserAdmin(Integer.parseInt(newUserId)));
        storage.getOrder(Integer.parseInt(id)).setService(storage.getService(Integer.parseInt(newServiceId)));
        return "redirect:/admin/orders";
    }
}
