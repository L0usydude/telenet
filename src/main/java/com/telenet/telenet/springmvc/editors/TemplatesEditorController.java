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
public class TemplatesEditorController {
    @Autowired
    Storage storage;
    public TemplatesEditorController(Storage storage1){
        storage = storage1;
    }
    @GetMapping("/templatesEditor")
    public String mainPage(Model model, @RequestParam(name = "id") int id){
        model.addAttribute("value",storage.getTemplate(id));
        return "modelsLists/templatesEditor";
    }
    @PostMapping("/templatesEditor")
    public String userUpdate(@RequestParam(name = "id") String id, @RequestParam(name = "name") String newName,@RequestParam(name = "description") String newDescription,
                             @RequestParam(name = "price") String newPrice,@RequestParam(name = "areaId") String newAreaId){
        storage.getTemplate(Integer.parseInt(id)).setName(newName);
        storage.getTemplate(Integer.parseInt(id)).setDescription(newDescription);
        storage.getTemplate(Integer.parseInt(id)).setPrice(Double.parseDouble(newPrice));
        storage.getTemplate(Integer.parseInt(id)).setArea(storage.getArea(Integer.parseInt(newAreaId)));
        return "redirect:/admin/templates";
    }
}
