package com.telenet.telenet.models.template;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.telenet.telenet.models.BaseEntity;
import com.telenet.telenet.models.area.Area;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@AllArgsConstructor @NoArgsConstructor @Getter
@Setter
@ToString
@Builder
@JsonTypeName("Template") @JsonInclude(JsonInclude.Include.NON_NULL)
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, property = "type")
public class Template extends BaseEntity {
    private String name;
    private String description;
    private double price;
    @Autowired
    private Area area;

    public Template(int id, String name, String description, double price, Area area) {
        super(id);
        this.name = name;
        this.description = description;
        this.price = price;
        this.area = area;
    }
}
