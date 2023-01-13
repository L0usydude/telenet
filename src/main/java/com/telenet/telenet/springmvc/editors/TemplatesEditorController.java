package com.telenet.telenet.springmvc.editors;

import com.telenet.telenet.models.enums.status.StatusEnum;
import com.telenet.telenet.utils.Storage;
import com.telenet.telenet.utils.database.AreaBase;
import com.telenet.telenet.utils.database.TemplateBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.SQLException;

@Controller
public class TemplatesEditorController {
    @Autowired
    TemplateBase templateBase;
    @GetMapping("/templatesEditor")
    public String mainPage(Model model, @RequestParam(name = "id") int id) throws SQLException {
        model.addAttribute("value",templateBase.getTemplate(id));
        return "modelsLists/templatesEditor";
    }
    @PostMapping("/templatesEditor")
    public String templateUpdate(@RequestParam(name = "id") String id, @RequestParam(name = "name") String newName,@RequestParam(name = "description") String newDescription,
                             @RequestParam(name = "price") String newPrice,@RequestParam(name = "areaId") String newAreaId) throws SQLException {
        templateBase.updateById(id,newName, newDescription, newPrice, newAreaId);
        return "redirect:/admin/templates";
    }
}
