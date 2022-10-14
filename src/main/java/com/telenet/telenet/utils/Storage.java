package com.telenet.telenet.utils;

import com.telenet.telenet.models.area.Area;
import com.telenet.telenet.models.order.Order;
import com.telenet.telenet.models.service.Service;
import com.telenet.telenet.models.template.Template;
import com.telenet.telenet.models.user.User;
import com.telenet.telenet.models.user.impl.UserImpl;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Map;

@Component @ComponentScan(basePackages = "java.util.HashMap")
public class Storage {
    Map<Integer, Area> AreaMap;
    Map<Integer, Order> OrderMap;
    Map<Integer, Service> ServiceMap;
    Map<Integer, Template> TemplateMap;
    Map<Integer, User> UserImplMap;
    Map<Integer, User> UserAdminMap;

    @Autowired
    public Storage(Map<Integer, Area> areaMap,
                   Map<Integer, Order> orderMap,
                   Map<Integer, Service> serviceMap,
                   Map<Integer, Template> templateMap,
                   Map<Integer, User> userImplMap,
                   Map<Integer, User> userAdminMap) {
        AreaMap = areaMap;
        OrderMap = orderMap;
        ServiceMap = serviceMap;
        TemplateMap = templateMap;
        UserImplMap = userImplMap;
        UserAdminMap = userAdminMap;
    }

    @PostConstruct
    public void test(){

    }

    public Area getArea(int id){
        return AreaMap.get(id);
    }
    public Order getOrder(int id){
        return OrderMap.get(id);
    }
    public Template getTemplate(int id){
        return TemplateMap.get(id);
    }
    public Service getService(int id){
        return ServiceMap.get(id);
    }
    public User getUserAdmin(int id){
        return UserAdminMap.get(id);
    }
    public User getUserImpl(int id){
        return UserImplMap.get(id);
    }

    public void updateArea(int id, Area newArea){
        AreaMap.replace(id, newArea);
    }
    public void updateOrder(int id, Order newOrder){
        OrderMap.replace(id, newOrder);
    }
    public void updateTemplate(int id, Template newTemplate){
        TemplateMap.replace(id, newTemplate);
    }
    public void updateService(int id, Service newService){
        ServiceMap.replace(id, newService);
    }
    public void updateUserAdmin(int id, User newUserAdmin){
        UserAdminMap.replace(id, newUserAdmin);
    }
    public void updateUserImpl(int id, User newUserImpl){
        UserImplMap.replace(id, newUserImpl);
    }

    public void delArea(int id){
        AreaMap.remove(id);
    }
    public void delOrder(int id){
        OrderMap.remove(id);
    }
    public void delTemplate(int id){
        TemplateMap.remove(id);
    }
    public void delService(int id){
        ServiceMap.remove(id);
    }
    public void delUserAdmin(int id){
        UserAdminMap.remove(id);
    }
    public void delUserImpl(int id){
        UserImplMap.remove(id);
    }

    public void addArea(Area newArea){
        AreaMap.put(newArea.getId(), newArea);
    }
    public void addOrder(Order newOrder){
        OrderMap.put(newOrder.getId(), newOrder);
    }
    public void addService(Service newService){
        ServiceMap.put(newService.getId(), newService);
    }
    public void addTemplate(Template newTemplate){
        TemplateMap.put(newTemplate.getId(), newTemplate);
    }
    public void  addUserImpl(User newUserImpl){
        UserImplMap.put(newUserImpl.getId(), newUserImpl);
    }
    public void addUserAdmin(User newUserAdmin){
        UserAdminMap.put(newUserAdmin.getId(), newUserAdmin);
    }
}
