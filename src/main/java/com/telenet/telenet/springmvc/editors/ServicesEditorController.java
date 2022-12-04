package com.telenet.telenet.springmvc.editors;

import com.telenet.telenet.models.enums.status.StatusEnum;
import com.telenet.telenet.utils.Storage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ServicesEditorController {
    @Autowired
    Storage storage;
    public ServicesEditorController(Storage storage1){
        storage = storage1;
    }

    @GetMapping("/servicesEditor")
    public String mainPage(Model model, @RequestParam(name = "id") int id){
        model.addAttribute("value",storage.getService(id));
        return "modelsLists/servicesEditor";
    }
    @PostMapping("/servicesEditor")
    public String userUpdate(@RequestParam(name = "id") String id, @RequestParam(name = "name") String newName,@RequestParam(name = "description") String newDescription,
    @RequestParam(name = "price") String newPrice,@RequestParam(name = "templateId") String newTemplateId,
                             @RequestParam(name = "userLogin") String newUserLogin,@RequestParam(name = "status") String newStatus){
        storage.getService(Integer.parseInt(id)).setName(newName);
        storage.getService(Integer.parseInt(id)).setDescription(newDescription);
        storage.getService(Integer.parseInt(id)).setPrice(Double.parseDouble(newPrice));
        storage.getService(Integer.parseInt(id)).setTemplate(storage.getTemplate(Integer.parseInt(newTemplateId)));
        storage.getService(Integer.parseInt(id)).setUser(storage.getUserImplByLogin(newUserLogin));
        storage.getService(Integer.parseInt(id)).setStatus(StatusEnum.valueOf(newStatus));

        return "redirect:/admin/services";
    }
}
