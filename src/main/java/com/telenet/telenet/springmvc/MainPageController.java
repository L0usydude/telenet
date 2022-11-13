package com.telenet.telenet.springmvc;

import com.telenet.telenet.utils.Storage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainPageController {
    @Autowired
    Storage storage;
    public MainPageController(Storage storage1){storage = storage1;};
    @GetMapping("/mainPage")
    public String mainPage(){
        return "/mainPage";
    }

}
