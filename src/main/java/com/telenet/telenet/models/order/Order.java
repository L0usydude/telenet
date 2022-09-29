package com.telenet.telenet.models.order;

import com.telenet.telenet.models.enums.action.ActionEnum;
import com.telenet.telenet.models.enums.status.StatusEnum;
import com.telenet.telenet.models.service.Service;
import com.telenet.telenet.models.user.impl.UserAdmin;

public interface Order {
    int getId();
    void setId(int newId);
    void setAdmin(UserAdmin newAdmin);
    UserAdmin getAdmin();
    Service getService();
    void setService(Service newService);
    StatusEnum getStatus();
    ActionEnum getAction();
    void setAction(ActionEnum newAction);
    void setStatus(StatusEnum newStatus);

}
