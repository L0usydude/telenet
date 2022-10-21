package com.telenet.telenet.models.order;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.telenet.telenet.models.BaseEntity;
import com.telenet.telenet.models.enums.action.ActionEnum;
import com.telenet.telenet.models.enums.status.StatusEnum;
import com.telenet.telenet.models.service.Service;
import com.telenet.telenet.models.user.User;
import lombok.*;

@Getter
@Setter
@ToString @NoArgsConstructor @AllArgsConstructor
@JsonTypeName("Order") @JsonInclude(JsonInclude.Include.NON_NULL)
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, property = "type")

public class Order extends BaseEntity {
    private User admin;
    private Service service;
    private StatusEnum status;
    private ActionEnum action;

    public Order(int id, User admin, Service service, StatusEnum status, ActionEnum action) {
        super(id);
        this.admin = admin;
        this.service = service;
        this.status = status;
        this.action = action;
    }
}
