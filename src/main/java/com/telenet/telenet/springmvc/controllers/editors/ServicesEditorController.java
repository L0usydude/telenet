package com.telenet.telenet.springmvc.controllers.editors;

import com.telenet.telenet.utils.database.ServiceDataBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.SQLException;

@Controller
public class ServicesEditorController {
    @Autowired
    ServiceDataBase serviceDataBase;

    @GetMapping("/servicesEditor")
    public String mainPage(Model model, @RequestParam(name = "id") int id) throws SQLException {
        model.addAttribute("value", serviceDataBase.getService(id));
        return "modelsLists/servicesEditor";
    }
    @PostMapping("/servicesEditor")
    public String userUpdate(@RequestParam(name = "id") String id, @RequestParam(name = "name") String newName,@RequestParam(name = "description") String newDescription,
    @RequestParam(name = "price") String newPrice,@RequestParam(name = "templateId") String newTemplateId,
                             @RequestParam(name = "userId") String newUserId,@RequestParam(name = "status") String newStatus) throws SQLException {
        serviceDataBase.updateById(id,newName,newDescription,newPrice,newTemplateId, newUserId, newStatus);

        return "redirect:/admin/services";
    }
}
