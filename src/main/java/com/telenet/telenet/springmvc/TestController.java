package com.telenet.telenet.springmvc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class TestController {
    @GetMapping("/test")
    public String testView(Model model, @RequestParam(value = "value", required = true, defaultValue = "23123123") String name){
        model.addAttribute("testP", name);
        return "test";
    }

}
