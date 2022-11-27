package com.telenet.telenet.models.service;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.telenet.telenet.models.BaseEntity;
import com.telenet.telenet.models.enums.status.StatusEnum;
import com.telenet.telenet.models.template.Template;
import com.telenet.telenet.models.user.User;
import lombok.*;

@AllArgsConstructor @NoArgsConstructor @Getter
@Setter
@ToString
@Builder
@JsonTypeName("Service") @JsonInclude(JsonInclude.Include.NON_NULL)
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, property = "type")

public class Service extends BaseEntity {
    private String name;
    private String description;
    private double price;
    private Template template;
    private User user;
    private StatusEnum status;

    public Service(int id, String name, String description, double price, Template template, User user, StatusEnum status) {
        super(id);
        this.name = name;
        this.description = description;
        this.price = price;
        this.template = template;
        this.user = user;
        this.status = status;
    }
}
