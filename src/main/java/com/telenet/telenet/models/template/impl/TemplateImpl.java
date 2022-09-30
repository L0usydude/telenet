package com.telenet.telenet.models.template.impl;

import com.telenet.telenet.models.area.Area;
import com.telenet.telenet.models.template.Template;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@AllArgsConstructor @NoArgsConstructor @Data @Component
public class TemplateImpl implements Template {
    private int id;
    private String name;
    private String description;
    private double price;
    @Autowired
    private Area area;
}
