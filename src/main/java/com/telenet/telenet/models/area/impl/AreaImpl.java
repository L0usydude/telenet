package com.telenet.telenet.models.area.impl;

import com.telenet.telenet.models.area.Area;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor @NoArgsConstructor @Data @Component
public class AreaImpl implements Area {
    private int id;
    private String name;
    private String description;
}
