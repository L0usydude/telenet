package com.telenet.telenet.utils;

import com.telenet.telenet.models.BaseEntity;
import com.telenet.telenet.models.area.Area;
import com.telenet.telenet.models.enums.roles.RoleEnum;
import com.telenet.telenet.models.enums.status.StatusEnum;
import com.telenet.telenet.models.order.Order;
import com.telenet.telenet.models.service.Service;
import com.telenet.telenet.models.template.Template;
import com.telenet.telenet.models.user.User;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.IOException;
import java.util.*;

@Component @ComponentScan(basePackages = "java.util.HashMap")
public class Storage {
    Map<Integer, Area> areaMap;
    Map<Integer, Order> orderMap;
    Map<Integer, Service> serviceMap;
    Map<Integer, Template> templateMap;
    Map<Integer, User> userImplMap;
    Map<Integer, User> userAdminMap;

//    @Autowired
//    public Storage(Map<Integer, Area> areaMap,
//                   Map<Integer, Order> orderMap,
//                   Map<Integer, Service> serviceMap,
//                   Map<Integer, Template> templateMap,
//                   Map<Integer, User> userImplMap,
//                   Map<Integer, User> userAdminMap) {
//        this.areaMap = areaMap;
//        this.orderMap = orderMap;
//        this.serviceMap = serviceMap;
//        this.templateMap = templateMap;
//        this.userImplMap = userImplMap;
//        this.userAdminMap = userAdminMap;
//    }

    @PostConstruct
    public void initMethod() throws IOException {
        System.out.println("init");
        areaMap = JsonMapper.deserialize(Area.class);
        orderMap = JsonMapper.deserialize(Order.class);
        serviceMap = JsonMapper.deserialize(Service.class);
        templateMap = JsonMapper.deserialize(Template.class);
        userAdminMap = JsonMapper.deserialize(RoleEnum.ADMIN);
        userImplMap = JsonMapper.deserialize(RoleEnum.DEFAULT_USER);
    }

    @PreDestroy
    public void destroyMethod() throws IOException {
        System.out.println("destroy");
        JsonMapper.serialize(areaMap, Area.class);
        JsonMapper.serialize(orderMap, Order.class);
        JsonMapper.serialize(templateMap, Template.class);
        JsonMapper.serialize(serviceMap, Service.class);
        JsonMapper.serialize(userAdminMap, RoleEnum.ADMIN);
        JsonMapper.serialize(userImplMap, RoleEnum.DEFAULT_USER);
    }

    public Area getArea(int id){
        return areaMap.get(id);
    }
    public Order getOrder(int id){
        return orderMap.get(id);
    }
    public Template getTemplate(int id){
        return templateMap.get(id);
    }
    public Service getService(int id){
        return serviceMap.get(id);
    }
    public User getUserAdmin(int id){
        return userAdminMap.get(id);
    }
    public User getUserImpl(int id){
        return userImplMap.get(id);
    }

    public void updateArea(int id, Area newArea){
        areaMap.replace(id, newArea);
    }
    public void updateOrder(int id, Order newOrder){
        orderMap.replace(id, newOrder);
    }
    public void updateTemplate(int id, Template newTemplate){
        templateMap.replace(id, newTemplate);
    }
    public void updateService(int id, Service newService){
        serviceMap.replace(id, newService);
    }
    public void updateUserAdmin(int id, User newUserAdmin){
        userAdminMap.replace(id, newUserAdmin);
    }
    public void updateUserImpl(int id, User newUserImpl){
        userImplMap.replace(id, newUserImpl);
    }

    public void delArea(int id){
        areaMap.remove(id);
    }
    public void delOrder(int id){
        orderMap.remove(id);
    }
    public void delTemplate(int id){
        templateMap.remove(id);
    }
    public void delService(int id){
        serviceMap.remove(id);
    }
    public void delUserAdmin(int id){
        userAdminMap.remove(id);
    }
    public void delUserImpl(int id){
        userImplMap.remove(id);
    }

    public void addArea(Area newArea){
        areaMap.put(newArea.getId(), newArea);
    }
    public void addOrder(Order newOrder){
        int max = orderMap.values().stream().mapToInt(BaseEntity::getId).max().orElseThrow(NoSuchElementException::new);
        newOrder.setId(max + 1);
        orderMap.put(newOrder.getId(), newOrder);
    }
    public void addService(Service newService){
        serviceMap.put(newService.getId(), newService);
    }
    public void addTemplate(Template newTemplate){
        templateMap.put(newTemplate.getId(), newTemplate);
    }
    public void addUserImpl(User newUserImpl){
        userImplMap.put(newUserImpl.getId(), newUserImpl);
    }
    public void addUserAdmin(User newUserAdmin){
        userAdminMap.put(newUserAdmin.getId(), newUserAdmin);
    }

    public Map<Integer, Area> getAreaMap() {
        return areaMap;
    }

    public Map<Integer, Order> getOrderMap() {
        return orderMap;
    }

    public Map<Integer, Service> getServiceMap() {
        return serviceMap;
    }

    public Map<Integer, Template> getTemplateMap() {
        return templateMap;
    }

    public Map<Integer, User> getUserImplMap() {
        return userImplMap;
    }

    public Map<Integer, User> getUserAdminMap() {
        return userAdminMap;
    }

    public User getUserImplByLogin(String name){
        return userImplMap.values().stream().filter(user -> Objects.equals(user.getLogin(), name)).findFirst().orElse(null);
    }
    public User getUserImplById(int id){
        return  userImplMap.values().stream().filter(user -> user.getId() == id).findFirst().orElse(null);
    }
    public User getAdminByLogin(String name){
        return userAdminMap.values().stream().filter(user -> Objects.equals(user.getLogin(), name)).findFirst().orElse(null);
    }
    public List<Service> getActiveServiceListByUserImplLogin(String login){
        return serviceMap.values().stream().filter(service -> login.equals(service.getUser().getLogin()) && service.getStatus().equals(StatusEnum.ACTIVE)).toList();
    }
    public List<Order> getOrderListByUserImplLogin(String login){
        return orderMap.values().stream().filter(order -> login.equals(order.getUser().getLogin())).toList();
    }
}
