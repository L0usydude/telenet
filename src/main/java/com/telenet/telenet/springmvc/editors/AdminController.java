package com.telenet.telenet.springmvc.editors;

import com.telenet.telenet.models.area.Area;
import com.telenet.telenet.models.order.Order;
import com.telenet.telenet.models.service.Service;
import com.telenet.telenet.models.template.Template;
import com.telenet.telenet.models.user.User;
import com.telenet.telenet.utils.Storage;
import com.telenet.telenet.utils.database.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

@Controller @RequestMapping("/admin")
public class AdminController {
    @Autowired
    UserADMINBase userADMINBase;
    @Autowired
    UserDEFAULT_USERBase userDEFAULT_userBase;
    @Autowired
    AreaBase areaBase;
    @Autowired
    TemplateBase templateBase;
    @Autowired
    ServiceBase serviceBase;
    @Autowired
    OrderBase orderBase;

    public static List<String> getClassFields(Class class_){
        Field[] newone = class_.getDeclaredFields();
        List<String> list = Arrays.stream(newone).map(Field::getName).collect(Collectors.toList());
        list.add("id");
        return list;
    }

    @GetMapping
    public String startAdmin(){
        return "users/admin";
    }
    @GetMapping("/admins")
    public String adminsOut(Model model) throws SQLException {
        if(!model.containsAttribute("adminsCollection")){
            model.addAttribute("adminsCollection", userADMINBase.getListOfUsers());
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
    @GetMapping("/users")
    public String usersOut(Model model) throws SQLException {
        if (!model.containsAttribute("usersCollection")){
            model.addAttribute("usersCollection", userDEFAULT_userBase.getListOfUsers());
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
    @GetMapping("/templates")
    public String templatesOut(Model model) throws SQLException {
        if (!model.containsAttribute("templatesCollection")){
            model.addAttribute("templatesCollection", templateBase.getListOfTemplates());
        }
        List<String> list2 = getClassFields(Template.class);
        model.addAttribute("fields", list2);
        List<String> list = getClassFields(Template.class);
        list.add("noSort");
        model.addAttribute("fieldsForSort", list);
        return "modelsLists/templates";
    }
    @GetMapping("/services")
    public String servicesOut(Model model) throws SQLException {
        if (!model.containsAttribute("servicesCollection")) {
            model.addAttribute("servicesCollection", serviceBase.getListOfServices());
        }
        List<String> list2 = getClassFields(Service.class);
        model.addAttribute("fields", list2);
        List<String> list = getClassFields(Service.class);
        list.add("noSort");
        model.addAttribute("fieldsForSort", list);
        return "modelsLists/services";
    }
    @GetMapping("/orders")
    public String ordersOut(Model model) throws SQLException {
        if (!model.containsAttribute("ordersCollection")) {
            model.addAttribute("ordersCollection", orderBase.getListOfOrders());
        }
        List<String> list2 = getClassFields(Order.class);
        model.addAttribute("fields", list2);
        List<String> list = getClassFields(Order.class);
        list.add("noSort");
        model.addAttribute("fieldsForSort", list);
        return "modelsLists/orders";
    }
    @GetMapping("/areas")
    public String areasOut(Model model) throws SQLException {
        if(!model.containsAttribute("areasCollection")){
            model.addAttribute("areasCollection", areaBase.getListOfAreas());
        }
        List<String> list2 = getClassFields(Area.class);
        model.addAttribute("fields", list2);
        List<String> list = getClassFields(Area.class);
        list.add("noSort");
        model.addAttribute("fieldsForSort", list);
        return "modelsLists/areas";
    }

    @PostMapping("/adminSearch")
    public String adminSearch(RedirectAttributes attributes, @RequestParam(name = "search") String search
            , @RequestParam(name = "field") String field
            , @RequestParam(name = "fieldForSort") String fieldForSort
            , @RequestParam(name = "upDown") String upDown) throws SQLException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method method = userADMINBase.getClass().getMethod(field + "Search", String.class);
        List<User> list = (List<User>) method.invoke(userADMINBase,search);
        String sth;
        List<User> list1 = null;
        switch (fieldForSort){
            case "id" -> {
                list1 = list.stream().sorted((o1, o2) -> {
                    if (upDown.equals("up")) {
                        return o1.getId() - o2.getId();
                    } else {
                        return o2.getId() - o1.getId();
                    }
                }).toList();
            }
        case "name" ->{
            list1 = list.stream().sorted((o1, o2) -> {
                if (upDown.equals("up")) {
                    return o1.getName().compareTo(o2.getName());
                } else {
                    return -o1.getName().compareTo(o2.getName());
                }
            }).toList();
            }
            case "login" ->{
                list1 = list.stream().sorted((o1, o2) -> {
                    if(upDown.equals("up")){
                        return o1.getLogin().compareTo(o2.getLogin());
                    }
                    else{
                        return - o1.getLogin().compareTo(o2.getLogin());
                    }
                }).toList();
            }
            case "password" -> {
                list1 = list.stream().sorted((o1, o2) -> {
                    if(upDown.equals("up")){
                        return o1.getPassword().compareTo(o2.getPassword());
                    }
                    else{
                        return - o1.getPassword().compareTo(o2.getPassword());
                    }
                }).toList();
            }
            case "noSort" ->
                    {
                        list1 = list;
                    }
        }

        attributes.addFlashAttribute("adminsCollection", list1);
        return "redirect:/admin/admins";
    }

    @PostMapping("/userSearch")
    public String userSearch(RedirectAttributes attributes, @RequestParam(name = "search") String search
            , @RequestParam(name = "field") String field
            , @RequestParam(name = "fieldForSort") String fieldForSort
            , @RequestParam(name = "upDown") String upDown) throws SQLException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method method = userDEFAULT_userBase.getClass().getMethod(field + "Search", String.class);
        List<User> list = (List<User>) method.invoke(userDEFAULT_userBase,search);
        String sth;
        List<User> list1 = null;
        switch (fieldForSort){
            case "id" -> {
                list1 = list.stream().sorted((o1, o2) -> {
                    if (upDown.equals("up")) {
                        return o1.getId() - o2.getId();
                    } else {
                        return o2.getId() - o1.getId();
                    }
                }).toList();
            }
            case "name" ->{
                list1 = list.stream().sorted((o1, o2) -> {
                    if (upDown.equals("up")) {
                        return o1.getName().compareTo(o2.getName());
                    } else {
                        return -o1.getName().compareTo(o2.getName());
                    }
                }).toList();
            }
            case "login" ->{
                list1 = list.stream().sorted((o1, o2) -> {
                    if(upDown.equals("up")){
                        return o1.getLogin().compareTo(o2.getLogin());
                    }
                    else{
                        return - o1.getLogin().compareTo(o2.getLogin());
                    }
                }).toList();
            }
            case "password" -> {
                list1 = list.stream().sorted((o1, o2) -> {
                    if(upDown.equals("up")){
                        return o1.getPassword().compareTo(o2.getPassword());
                    }
                    else{
                        return - o1.getPassword().compareTo(o2.getPassword());
                    }
                }).toList();
            }
            case "noSort" ->
                    {
                        list1 = list;
                    }
        }

        attributes.addFlashAttribute("usersCollection", list1);
        return "redirect:/admin/users";
    }

    @PostMapping("/areaSearch")
    public String areaSearch(RedirectAttributes attributes, @RequestParam(name = "search") String search
            , @RequestParam(name = "field") String field
            , @RequestParam(name = "fieldForSort") String fieldForSort
            , @RequestParam(name = "upDown") String upDown) throws SQLException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method method = areaBase.getClass().getMethod(field + "Search", String.class);
        List<Area> list = (List<Area>) method.invoke(areaBase, search);
        String sth;
        List<Area> list1 = null;
        switch (fieldForSort){
            case "id" -> {
                list1 = list.stream().sorted((o1, o2) -> {
                    if (upDown.equals("up")) {
                        return o1.getId() - o2.getId();
                    } else {
                        return o2.getId() - o1.getId();
                    }
                }).toList();
            }
            case "name" ->{
                list1 = list.stream().sorted((o1, o2) -> {
                    if (upDown.equals("up")) {
                        return o1.getName().compareTo(o2.getName());
                    } else {
                        return -o1.getName().compareTo(o2.getName());
                    }
                }).toList();
            }
            case "description" ->{
                list1 = list.stream().sorted((o1, o2) -> {
                    if(upDown.equals("up")){
                        return o1.getDescription().compareTo(o2.getDescription());
                    }
                    else{
                        return - o1.getDescription().compareTo(o2.getDescription());
                    }
                }).toList();
            }
            case "noSort" ->
                    {
                        list1 = list;
                    }
        }
        attributes.addFlashAttribute("areasCollection",list1);
        return "redirect:/admin/areas";
    }

    @PostMapping("/templateSearch")
    public String templateSearch(RedirectAttributes attributes, @RequestParam(name = "search") String search
            , @RequestParam(name =  "field") String field
            , @RequestParam(name = "fieldForSort") String fieldForSort
            , @RequestParam(name = "upDown") String upDown) throws SQLException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method method = templateBase.getClass().getMethod(field + "Search", String.class);
        List<Template> list = (List<Template>) method.invoke(templateBase,search);
        String sth;
        List<Template> list1 = null;
        switch (fieldForSort){
            case "id" -> {
                list1 = list.stream().sorted((o1, o2) -> {
                    if (upDown.equals("up")) {
                        return o1.getId() - o2.getId();
                    } else {
                        return o2.getId() - o1.getId();
                    }
                }).toList();
            }
            case "name" ->{
                list1 = list.stream().sorted((o1, o2) -> {
                    if (upDown.equals("up")) {
                        return o1.getName().compareTo(o2.getName());
                    } else {
                        return -o1.getName().compareTo(o2.getName());
                    }
                }).toList();
            }
            case "description" ->{
                list1 = list.stream().sorted((o1, o2) -> {
                    if(upDown.equals("up")){
                        return o1.getDescription().compareTo(o2.getDescription());
                    }
                    else{
                        return - o1.getDescription().compareTo(o2.getDescription());
                    }
                }).toList();
            }
            case "price" -> {
                list1 = list.stream().sorted((o1, o2) -> {
                    if(upDown.equals("up")){
                        return (int) (o1.getPrice() - o2.getPrice());
                    }
                    else{
                        return (int) (o2.getPrice() - o1.getPrice());
                    }
                }).toList();
            }
            case "area" -> {
                list1 = list.stream().sorted((o1, o2) -> {
                    if (upDown.equals("up")){
                        return o1.getArea().getId() - o2.getArea().getId();
                    }
                    else {
                        return o2.getArea().getId()-o1.getArea().getId();
                    }
                }).toList();
            }
            case "noSort" ->
                    {
                        list1 = list;
                    }
        }
        attributes.addFlashAttribute("templatesCollection",list1);
        return "redirect:/admin/templates";
    }

    @PostMapping("/serviceSearch")
    public String serviceSearch(RedirectAttributes attributes, @RequestParam(name = "search") String search
            , @RequestParam(name =  "field") String field
            , @RequestParam(name = "fieldForSort") String fieldForSort
            , @RequestParam(name = "upDown") String upDown) throws SQLException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method method = serviceBase.getClass().getMethod(field + "Search", String.class);
        List<Service> list = (List<Service>) method.invoke(serviceBase,search);
        String sth;
        List<Service> list1 = null;
        switch (fieldForSort){
            case "id" -> {
                list1 = list.stream().sorted((o1, o2) -> {
                    if (upDown.equals("up")) {
                        return o1.getId() - o2.getId();
                    } else {
                        return o2.getId() - o1.getId();
                    }
                }).toList();
            }
            case "name" ->{
                list1 = list.stream().sorted((o1, o2) -> {
                    if (upDown.equals("up")) {
                        return o1.getName().compareTo(o2.getName());
                    } else {
                        return -o1.getName().compareTo(o2.getName());
                    }
                }).toList();
            }
            case "description" ->{
                list1 = list.stream().sorted((o1, o2) -> {
                    if(upDown.equals("up")){
                        return o1.getDescription().compareTo(o2.getDescription());
                    }
                    else{
                        return - o1.getDescription().compareTo(o2.getDescription());
                    }
                }).toList();
            }
            case "price" -> {
                list1 = list.stream().sorted((o1, o2) -> {
                    if(upDown.equals("up")){
                        return (int) (o1.getPrice() - o2.getPrice());
                    }
                    else{
                        return (int) (o2.getPrice() - o1.getPrice());
                    }
                }).toList();
            }
            case "template" -> {
                list1 = list.stream().sorted((o1, o2) -> {
                    if (upDown.equals("up")){
                        return o1.getTemplate().getId() - o2.getTemplate().getId();
                    }
                    else {
                        return o2.getTemplate().getId() - o1.getTemplate().getId();
                    }
                }).toList();
            }
            case "user" -> {
                list1 = list.stream().sorted((o1, o2) -> {
                    if (upDown.equals("up")){
                        return o1.getUser().getId() - o2.getUser().getId();
                    }
                    else {
                        return o2.getUser().getId() - o1.getUser().getId();
                    }
                }).toList();
            }
            case "status" -> {
                list1 = list.stream().sorted((o1,o2) ->{
                    if (upDown.equals("up")){
                        return o1.getStatus().compareTo(o2.getStatus());
                    }
                    else {
                        return -o1.getStatus().compareTo(o2.getStatus());
                    }
                }).toList();
            }
            case "noSort" ->
                    {
                        list1 = list;
                    }
        }
        attributes.addFlashAttribute("servicesCollection",list1);
        return "redirect:/admin/services";
    }

    @PostMapping("/orderSearch")
    public String orderSearch(RedirectAttributes attributes, @RequestParam(name = "search") String search
            , @RequestParam(name =  "field") String field
            , @RequestParam(name = "fieldForSort") String fieldForSort
            , @RequestParam(name = "upDown") String upDown) throws SQLException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method method = orderBase.getClass().getMethod(field + "Search", String.class);
        List<Order> list = (List<Order>) method.invoke(orderBase,search);
        String sth;
        List<Order> list1 = null;
        switch (fieldForSort){
            case "id" -> {
                list1 = list.stream().sorted((o1, o2) -> {
                    if (upDown.equals("up")) {
                        return o1.getId() - o2.getId();
                    } else {
                        return o2.getId() - o1.getId();
                    }
                }).toList();
            }
            case "user" ->{
                list1 = list.stream().sorted((o1, o2) -> {
                    if (upDown.equals("up")) {
                        return o1.getUser().getId() - o2.getUser().getId();
                    } else {
                        return o2.getUser().getId() - o1.getUser().getId();
                    }
                }).toList();
            }
            case "status" ->{
                list1 = list.stream().sorted((o1, o2) -> {
                    if(upDown.equals("up")){
                        return o1.getStatus().compareTo(o2.getStatus());
                    }
                    else{
                        return - o1.getStatus().compareTo(o2.getStatus());
                    }
                }).toList();
            }
            case "service" -> {
                list1 = list.stream().sorted((o1, o2) -> {
                    if(upDown.equals("up")){
                        return o1.getService().getId() - o2.getService().getId();
                    }
                    else{
                        return o2.getService().getId() - o1.getService().getId();
                    }
                }).toList();
            }
            case "action" -> {
                list1 = list.stream().sorted((o1, o2) -> {
                    if (upDown.equals("up")){
                        return o1.getAction().compareTo(o2.getAction());
                    }
                    else {
                        return -o1.getAction().compareTo(o2.getAction());
                    }
                }).toList();
            }
            case "noSort" ->
                    {
                        list1 = list;
                    }
        }
        attributes.addFlashAttribute("ordersCollection",list1);
        return "redirect:/admin/orders";
    }
}
