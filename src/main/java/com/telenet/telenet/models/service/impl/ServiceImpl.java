package com.telenet.telenet.models.service.impl;

import com.telenet.telenet.models.enums.status.StatusEnum;
import com.telenet.telenet.models.service.Service;
import com.telenet.telenet.models.template.Template;
import com.telenet.telenet.models.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor @NoArgsConstructor @Data

public class ServiceImpl implements Service {
    private int id;
    private String name;
    private String description;
    private double price;
    private Template template;
    private User user;
    private StatusEnum status;
}
