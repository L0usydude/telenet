package com.telenet.telenet.springmvc.editors;

import com.telenet.telenet.utils.Storage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AreasEditorController {
    @Autowired
    Storage storage;
    public AreasEditorController(Storage storage1){
        storage = storage1;
    }
    @GetMapping("/areasEditor")
    public String mainPage(Model model, @RequestParam(name = "id") int id){
        model.addAttribute("value",storage.getArea(id));
        return "modelsLists/areasEditor";
    }
    @PostMapping("/areasEditor")
    public String userUpdate(@RequestParam(name = "id") String id, @RequestParam(name = "name") String newName,@RequestParam(name = "description") String newDescription){
        storage.getArea(Integer.parseInt(id)).setName(newName);
        storage.getArea(Integer.parseInt(id)).setDescription(newDescription);

        return "redirect:/admin/areas";
    }
}
