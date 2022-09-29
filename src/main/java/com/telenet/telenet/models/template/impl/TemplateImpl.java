package com.telenet.telenet.models.template.impl;

import com.telenet.telenet.models.area.Area;
import com.telenet.telenet.models.template.Template;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor @NoArgsConstructor @RequiredArgsConstructor @Data
public class TemplateImpl implements Template {
    int id;
    String name;
    String description;
    double price;
    Area area;
}
