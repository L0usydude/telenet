package com.telenet.telenet.springmvc.services;

import com.telenet.telenet.models.BaseEntity;
import com.telenet.telenet.models.area.Area;
import com.telenet.telenet.models.order.Order;
import com.telenet.telenet.models.service.Service;
import com.telenet.telenet.models.template.Template;
import com.telenet.telenet.models.user.User;
import com.telenet.telenet.utils.database.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@org.springframework.stereotype.Service
public class AdminService {
    @Autowired
    UserADMINDataBase userADMINDataBase;
    @Autowired
    UserDEFAULT_USERDataBase userDEFAULT_userDataBase;
    @Autowired
    AreaDataBase areaDataBase;
    @Autowired
    TemplateDataBase templateDataBase;
    @Autowired
    ServiceDataBase serviceDataBase;
    @Autowired
    OrderDataBase orderDataBase;

    public static List<String> getClassFields(Class class_){
        Field[] newone = class_.getDeclaredFields();
        List<String> list = Arrays.stream(newone).map(Field::getName).collect(Collectors.toList());
        list.add("id");
        return list;
    }
    public String startAdmin(){
        return "users/admin";
    }
    public String adminsOut(Model model) throws SQLException {
        if(!model.containsAttribute("adminsCollection")){
            model.addAttribute("adminsCollection", userADMINDataBase.getListOfUsers());
        }
        List<String> list2 = getClassFields(User.class);
        list2.remove(3);
        model.addAttribute("fields", list2);
        List<String> list = getClassFields(User.class);
        list.remove(3);
        list.add("noSort");
        model.addAttribute("fieldsForSort", list);
        return "modelsLists/admins";
    }
    public String usersOut(Model model) throws SQLException {
        if (!model.containsAttribute("usersCollection")){
            model.addAttribute("usersCollection", userDEFAULT_userDataBase.getListOfUsers());
        }
        List<String> list2 = getClassFields(User.class);
        list2.remove(3);
        model.addAttribute("fields", list2);
        List<String> list = getClassFields(User.class);
        list.remove(3);
        list.add("noSort");
        model.addAttribute("fieldsForSort", list);
        return "modelsLists/users";
    }
    public String templatesOut(Model model) throws SQLException {
        if (!model.containsAttribute("templatesCollection")){
            model.addAttribute("templatesCollection", templateDataBase.getListOfTemplates());
        }
        List<String> list2 = getClassFields(Template.class);
        model.addAttribute("fields", list2);
        List<String> list = getClassFields(Template.class);
        list.add("noSort");
        model.addAttribute("fieldsForSort", list);
        return "modelsLists/templates";
    }
    public String servicesOut(Model model) throws SQLException {
        if (!model.containsAttribute("servicesCollection")) {
            model.addAttribute("servicesCollection", serviceDataBase.getListOfServices());
        }
        List<String> list2 = getClassFields(Service.class);
        model.addAttribute("fields", list2);
        List<String> list = getClassFields(Service.class);
        list.add("noSort");
        model.addAttribute("fieldsForSort", list);
        return "modelsLists/services";
    }
    public String ordersOut(Model model) throws SQLException {
        if (!model.containsAttribute("ordersCollection")) {
            model.addAttribute("ordersCollection", orderDataBase.getListOfOrders());
        }
        List<String> list2 = getClassFields(Order.class);
        model.addAttribute("fields", list2);
        List<String> list = getClassFields(Order.class);
        list.add("noSort");
        model.addAttribute("fieldsForSort", list);
        return "modelsLists/orders";
    }
    public String areasOut(Model model) throws SQLException {
        if(!model.containsAttribute("areasCollection")){
            model.addAttribute("areasCollection", areaDataBase.getListOfAreas());
        }
        List<String> list2 = getClassFields(Area.class);
        model.addAttribute("fields", list2);
        List<String> list = getClassFields(Area.class);
        list.add("noSort");
        model.addAttribute("fieldsForSort", list);
        return "modelsLists/areas";
    }
    public String adminSearch(RedirectAttributes attributes, @RequestParam(name = "search") String search
            , @RequestParam(name = "field") String field
            , @RequestParam(name = "fieldForSort") String fieldForSort
            , @RequestParam(name = "upDown") String upDown) throws SQLException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method method = userADMINDataBase.getClass().getMethod(field + "Search", String.class);
        List<User> list = (List<User>) method.invoke(userADMINDataBase,search);
        String sth;
        List<User> list1 = null;
        switch (fieldForSort){
            case "id" -> {
                list1 = sortId(list,upDown);
            }
            case "name" ->{
                list1 = sortUserByName(list,upDown);
            }
            case "login" ->{
                list1 = sortUserByLogin(list,upDown);
            }
            case "password" -> {
                list1 = sortUserByPassword(list,upDown);
            }
            case "noSort" ->
                    {
                        list1 = list;
                    }
        }

        attributes.addFlashAttribute("adminsCollection", list1);
        return "redirect:/admin/admins";
    }
    public String userSearch(RedirectAttributes attributes, @RequestParam(name = "search") String search
            , @RequestParam(name = "field") String field
            , @RequestParam(name = "fieldForSort") String fieldForSort
            , @RequestParam(name = "upDown") String upDown) throws SQLException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method method = userDEFAULT_userDataBase.getClass().getMethod(field + "Search", String.class);
        List<User> list = (List<User>) method.invoke(userDEFAULT_userDataBase,search);
        String sth;
        List<User> list1 = null;
        switch (fieldForSort){
            case "id" -> {
                list1 = sortId(list,upDown);
            }
            case "name" ->{
                list1 = sortUserByName(list,upDown);
            }
            case "login" ->{
                list1 = sortUserByLogin(list,upDown);
            }
            case "password" -> {
                list1 = sortUserByPassword(list,upDown);
            }
            case "noSort" ->
                    {
                        list1 = list;
                    }
        }

        attributes.addFlashAttribute("usersCollection", list1);
        return "redirect:/admin/users";
    }
    public String areaSearch(RedirectAttributes attributes, @RequestParam(name = "search") String search
            , @RequestParam(name = "field") String field
            , @RequestParam(name = "fieldForSort") String fieldForSort
            , @RequestParam(name = "upDown") String upDown) throws SQLException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method method = areaDataBase.getClass().getMethod(field + "Search", String.class);
        List<Area> list = (List<Area>) method.invoke(areaDataBase, search);
        String sth;
        List<Area> list1 = null;
        switch (fieldForSort){
            case "id" -> {
                list1 = sortId(list,upDown);
            }
            case "name" ->{
                list1 = sortAreaByName(list,upDown);
            }
            case "description" ->{
                list1 = sortAreaByDescription(list,upDown);
            }
            case "noSort" ->
                    {
                        list1 = list;
                    }
        }
        attributes.addFlashAttribute("areasCollection",list1);
        return "redirect:/admin/areas";
    }
    public String templateSearch(RedirectAttributes attributes, @RequestParam(name = "search") String search
            , @RequestParam(name =  "field") String field
            , @RequestParam(name = "fieldForSort") String fieldForSort
            , @RequestParam(name = "upDown") String upDown) throws SQLException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method method = templateDataBase.getClass().getMethod(field + "Search", String.class);
        List<Template> list = (List<Template>) method.invoke(templateDataBase,search);
        String sth;
        List<Template> list1 = null;
        switch (fieldForSort){
            case "id" -> {
                list1 = sortId(list,upDown);
            }
            case "name" ->{
                list1 = sortTemplateByName(list,upDown);
            }
            case "description" ->{
                list1 = sortTemplateByDescription(list,upDown);
            }
            case "price" -> {
                list1 = sortTemplateByPrice(list,upDown);
            }
            case "area" -> {
                list1 = sortTemplateByArea(list,upDown);
            }
            case "noSort" ->
                    {
                        list1 = list;
                    }
        }
        attributes.addFlashAttribute("templatesCollection",list1);
        return "redirect:/admin/templates";
    }
    public String serviceSearch(RedirectAttributes attributes, @RequestParam(name = "search") String search
            , @RequestParam(name =  "field") String field
            , @RequestParam(name = "fieldForSort") String fieldForSort
            , @RequestParam(name = "upDown") String upDown) throws SQLException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method method = serviceDataBase.getClass().getMethod(field + "Search", String.class);
        List<Service> list = (List<Service>) method.invoke(serviceDataBase,search);
        String sth;
        List<Service> list1 = null;
        switch (fieldForSort){
            case "id" -> {
                list1 = sortId(list,upDown);
            }
            case "name" ->{
                list1 = sortServiceByName(list,upDown);
            }
            case "description" ->{
                list1 = sortServiceByDescription(list,upDown);
            }
            case "price" -> {
                list1 = sortServiceByPrice(list,upDown);
            }
            case "template" -> {
                list1 = sortServiceByTemplate(list,upDown);
            }
            case "user" -> {
                list1 = sortServiceByUser(list,upDown);
            }
            case "status" -> {
                list1 = sortServiceByStatus(list,upDown);
            }
            case "noSort" ->
                    {
                        list1 = list;
                    }
        }
        attributes.addFlashAttribute("servicesCollection",list1);
        return "redirect:/admin/services";
    }
    public String orderSearch(RedirectAttributes attributes, @RequestParam(name = "search") String search
            , @RequestParam(name =  "field") String field
            , @RequestParam(name = "fieldForSort") String fieldForSort
            , @RequestParam(name = "upDown") String upDown) throws SQLException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method method = orderDataBase.getClass().getMethod(field + "Search", String.class);
        List<Order> list = (List<Order>) method.invoke(orderDataBase,search);
        String sth;
        List<Order> list1 = null;
        switch (fieldForSort){
            case "id" -> {
                list1 = sortId(list,upDown);
            }
            case "user" ->{
                list1 = sortOrderByUser(list,upDown);
            }
            case "status" ->{
                list1 = sortOrderByStatus(list,upDown);
            }
            case "service" -> {
                list1 = sortOrderByService(list,upDown);
            }
            case "action" -> {
                list1 = sortOrderByAction(list,upDown);
            }
            case "noSort" -> {
                        list1 = list;
            }
        }
        attributes.addFlashAttribute("ordersCollection",list1);
        return "redirect:/admin/orders";
    }

    public <T extends BaseEntity> List<T> sortId(List<T> list, String upDown){
        return list.stream().sorted((o1, o2) -> {
            if (upDown.equals("up")) {
                return o1.getId() - o2.getId();
            } else {
                return o2.getId() - o1.getId();
            }
        }).toList();
    }

    public <T extends User> List<T> sortUserByName(List<T> list, String upDown){
        return list.stream().sorted((o1, o2) -> {
            if (upDown.equals("up")) {
                return o1.getName().compareTo(o2.getName());
            } else {
                return -o1.getName().compareTo(o2.getName());
            }
        }).toList();
    }

    public <T extends User> List<T> sortUserByLogin(List<T> list, String upDown){
        return list.stream().sorted((o1, o2) -> {
            if(upDown.equals("up")){
                return o1.getLogin().compareTo(o2.getLogin());
            }
            else{
                return - o1.getLogin().compareTo(o2.getLogin());
            }
        }).toList();
    }

    public <T extends User> List<T> sortUserByPassword(List<T> list, String upDown){
        return list.stream().sorted((o1, o2) -> {
            if(upDown.equals("up")){
                return o1.getPassword().compareTo(o2.getPassword());
            }
            else{
                return - o1.getPassword().compareTo(o2.getPassword());
            }
        }).toList();
    }

    public <T extends Area> List<T> sortAreaByName(List<T> list, String upDown){
        return list.stream().sorted((o1, o2) -> {
            if (upDown.equals("up")) {
                return o1.getName().compareTo(o2.getName());
            } else {
                return -o1.getName().compareTo(o2.getName());
            }
        }).toList();
    }

    public <T extends Area> List<T> sortAreaByDescription(List<T> list, String upDown){
        return list.stream().sorted((o1, o2) -> {
            if(upDown.equals("up")){
                return o1.getDescription().compareTo(o2.getDescription());
            }
            else{
                return - o1.getDescription().compareTo(o2.getDescription());
            }
        }).toList();
    }


    public <T extends Template> List<T> sortTemplateByName(List<T> list,String upDown){
        return list.stream().sorted((o1, o2) -> {
            if (upDown.equals("up")) {
                return o1.getName().compareTo(o2.getName());
            } else {
                return -o1.getName().compareTo(o2.getName());
            }
        }).toList();
    }

    public <T extends Template> List<T> sortTemplateByDescription(List<T> list,String upDown){
        return list.stream().sorted((o1, o2) -> {
            if(upDown.equals("up")){
                return o1.getDescription().compareTo(o2.getDescription());
            }
            else{
                return - o1.getDescription().compareTo(o2.getDescription());
            }
        }).toList();
    }

    public <T extends Template> List<T> sortTemplateByPrice(List<T> list,String upDown){
        return list.stream().sorted((o1, o2) -> {
            if(upDown.equals("up")){
                return (int) (o1.getPrice() - o2.getPrice());
            }
            else{
                return (int) (o2.getPrice() - o1.getPrice());
            }
        }).toList();
    }

    public <T extends Template> List<T> sortTemplateByArea(List<T> list,String upDown){
        return list.stream().sorted((o1, o2) -> {
            if (upDown.equals("up")){
                return o1.getArea().getId() - o2.getArea().getId();
            }
            else {
                return o2.getArea().getId()-o1.getArea().getId();
            }
        }).toList();
    }

    public <T extends Service> List<T> sortServiceByName(List<T> list, String upDown){
        return list.stream().sorted((o1, o2) -> {
            if (upDown.equals("up")) {
                return o1.getName().compareTo(o2.getName());
            } else {
                return -o1.getName().compareTo(o2.getName());
            }
        }).toList();
    }

    public <T extends Service> List<T> sortServiceByDescription(List<T> list, String upDown){
        return list.stream().sorted((o1, o2) -> {
            if(upDown.equals("up")){
                return o1.getDescription().compareTo(o2.getDescription());
            }
            else{
                return - o1.getDescription().compareTo(o2.getDescription());
            }
        }).toList();
    }

    public <T extends Service> List<T> sortServiceByStatus(List<T> list, String upDown){
        return list.stream().sorted((o1,o2) ->{
            if (upDown.equals("up")){
                return o1.getStatus().compareTo(o2.getStatus());
            }
            else {
                return -o1.getStatus().compareTo(o2.getStatus());
            }
        }).toList();
    }

    public <T extends Service> List<T> sortServiceByTemplate(List<T> list, String upDown){
        return list.stream().sorted((o1, o2) -> {
            if (upDown.equals("up")){
                return o1.getTemplate().getId() - o2.getTemplate().getId();
            }
            else {
                return o2.getTemplate().getId() - o1.getTemplate().getId();
            }
        }).toList();
    }

    public <T extends Service> List<T> sortServiceByUser(List<T> list, String upDown){
        return list.stream().sorted((o1, o2) -> {
            if (upDown.equals("up")){
                return o1.getUser().getId() - o2.getUser().getId();
            }
            else {
                return o2.getUser().getId() - o1.getUser().getId();
            }
        }).toList();
    }

    public <T extends Service> List<T> sortServiceByPrice(List<T> list, String upDown){
        return list.stream().sorted((o1, o2) -> {
            if(upDown.equals("up")){
                return (int) (o1.getPrice() - o2.getPrice());
            }
            else{
                return (int) (o2.getPrice() - o1.getPrice());
            }
        }).toList();
    }

    public <T extends Order> List<T> sortOrderByUser(List<T> list, String upDown){
        return  list.stream().sorted((o1, o2) -> {
            if (upDown.equals("up")) {
                return o1.getUser().getId() - o2.getUser().getId();
            } else {
                return o2.getUser().getId() - o1.getUser().getId();
            }
        }).toList();
    }


    public <T extends Order> List<T> sortOrderByStatus(List<T> list, String upDown){
        return list.stream().sorted((o1, o2) -> {
            if(upDown.equals("up")){
                return o1.getStatus().compareTo(o2.getStatus());
            }
            else{
                return - o1.getStatus().compareTo(o2.getStatus());
            }
        }).toList();
    }
    public <T extends Order> List<T> sortOrderByService(List<T> list, String upDown){
        return list.stream().sorted((o1, o2) -> {
            if(upDown.equals("up")){
                return o1.getService().getId() - o2.getService().getId();
            }
            else{
                return o2.getService().getId() - o1.getService().getId();
            }
        }).toList();
    }
    public <T extends Order> List<T> sortOrderByAction(List<T> list, String upDown){
        return list.stream().sorted((o1, o2) -> {
            if (upDown.equals("up")){
                return o1.getAction().compareTo(o2.getAction());
            }
            else {
                return -o1.getAction().compareTo(o2.getAction());
            }
        }).toList();
    }

}
