package com.telenet.telenet.models.service;

import com.telenet.telenet.models.enums.status.StatusEnum;
import com.telenet.telenet.models.template.Template;
import com.telenet.telenet.models.user.User;
import com.telenet.telenet.models.user.impl.UserImpl;


public interface Service{
    int getId();
    String getName();
    String getDescription();
    double getPrice();
    Template getTemplate();
    User getUser();
    StatusEnum getStatus();
    void setId(int newId);
    void setName(String newName);
    void setDescription(String newDescription);
    void setPrice(double newPrice);
    void setTemplate(Template newTemplate);
    void setUser(User newUser);
    void setStatus(StatusEnum newStatus);
}
