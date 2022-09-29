package com.telenet.telenet.models.service.impl;

import com.telenet.telenet.models.enums.status.StatusEnum;
import com.telenet.telenet.models.service.Service;
import com.telenet.telenet.models.template.Template;
import com.telenet.telenet.models.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor @NoArgsConstructor @RequiredArgsConstructor @Data

public class ServiceImpl implements Service {
    int id;
    String name;
    String description;
    double price;
    Template template;
    User user;
    StatusEnum status;
}
