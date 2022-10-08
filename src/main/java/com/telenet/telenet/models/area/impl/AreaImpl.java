package com.telenet.telenet.models.area.impl;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.telenet.telenet.models.area.Area;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor @NoArgsConstructor @Data @Component @JsonTypeName("AreaImplJson") @JsonInclude(JsonInclude.Include.NON_NULL)
public class AreaImpl implements Area {
    private int id;
    private String name;
    private String description;
}
