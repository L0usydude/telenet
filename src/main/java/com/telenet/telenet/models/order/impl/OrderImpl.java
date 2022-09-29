package com.telenet.telenet.models.order.impl;

import com.telenet.telenet.models.enums.action.ActionEnum;
import com.telenet.telenet.models.enums.status.StatusEnum;
import com.telenet.telenet.models.service.Service;
import com.telenet.telenet.models.user.impl.UserAdmin;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data @RequiredArgsConstructor @NoArgsConstructor @AllArgsConstructor

public class OrderImpl {
    int id;
    UserAdmin admin;
    Service service;
    StatusEnum status;
    ActionEnum action;
}
