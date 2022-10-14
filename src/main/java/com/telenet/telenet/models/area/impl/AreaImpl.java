package com.telenet.telenet.models.area.impl;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.telenet.telenet.models.BaseEntity;
import com.telenet.telenet.models.area.Area;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor @NoArgsConstructor @Data @Component @JsonTypeName("AreaImpl") @JsonInclude(JsonInclude.Include.NON_NULL)
public class AreaImpl extends BaseEntity implements Area {
    private String name;
    private String description;

    public AreaImpl(int id, String name, String description){
        super(id);
        this.name = name;
        this.description = description;
    }

}
