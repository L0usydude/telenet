package com.telenet.telenet.springmvc.controllers.editors;

import com.telenet.telenet.utils.database.AreaDataBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.SQLException;

@Controller
public class AreasEditorController {
    @Autowired
    AreaDataBase areaDataBase;
    @GetMapping("/areasEditor")
    public String mainPage(Model model, @RequestParam(name = "id") int id) throws SQLException {
        model.addAttribute("value", areaDataBase.getArea(id));
        return "modelsLists/areasEditor";
    }
    @PostMapping("/areasEditor")
    public String userUpdate(@RequestParam(name = "id") String id, @RequestParam(name = "name") String newName,@RequestParam(name = "description") String newDescription) throws SQLException {
        areaDataBase.updateById(id,newName,newDescription);
        return "redirect:/admin/areas";
    }
}
