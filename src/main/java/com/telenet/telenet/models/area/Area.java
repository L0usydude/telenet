package com.telenet.telenet.models.area;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.telenet.telenet.models.BaseEntity;
import lombok.*;
import org.springframework.stereotype.Component;

@AllArgsConstructor @NoArgsConstructor @Getter @Setter @ToString
@JsonTypeName("Area") @JsonInclude(JsonInclude.Include.NON_NULL)
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, property = "type")
public class Area extends BaseEntity {
    private String name;
    private String description;

    public Area(int id, String name, String description){
        super(id);
        this.name = name;
        this.description = description;
    }

}
