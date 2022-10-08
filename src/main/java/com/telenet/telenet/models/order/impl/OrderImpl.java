package com.telenet.telenet.models.order.impl;

import com.telenet.telenet.models.enums.action.ActionEnum;
import com.telenet.telenet.models.enums.status.StatusEnum;
import com.telenet.telenet.models.order.Order;
import com.telenet.telenet.models.service.Service;
import com.telenet.telenet.models.user.impl.UserAdmin;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor

public class OrderImpl  implements Order {
    private int id;
    private UserAdmin admin;
    private Service service;
    private StatusEnum status;
    private ActionEnum action;
}
