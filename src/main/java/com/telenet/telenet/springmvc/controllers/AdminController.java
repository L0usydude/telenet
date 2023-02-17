package com.telenet.telenet.springmvc.controllers;

import com.telenet.telenet.models.area.Area;
import com.telenet.telenet.models.order.Order;
import com.telenet.telenet.models.service.Service;
import com.telenet.telenet.models.template.Template;
import com.telenet.telenet.models.user.User;
import com.telenet.telenet.springmvc.services.AdminService;
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
    AdminService adminService;

    public static List<String> getClassFields(Class class_){
        return AdminService.getClassFields(class_);
    }

    @GetMapping
    public String startAdmin(){
        return "users/admin";
    }
    @GetMapping("/admins")
    public String adminsOut(Model model) throws SQLException {
        return adminService.adminsOut(model);
    }
    @GetMapping("/users")
    public String usersOut(Model model) throws SQLException {
        return adminService.usersOut(model);
    }
    @GetMapping("/templates")
    public String templatesOut(Model model) throws SQLException {
        return adminService.templatesOut(model);
    }
    @GetMapping("/services")
    public String servicesOut(Model model) throws SQLException {
        return  adminService.servicesOut(model);
    }
    @GetMapping("/orders")
    public String ordersOut(Model model) throws SQLException {
        return adminService.ordersOut(model);
    }
    @GetMapping("/areas")
    public String areasOut(Model model) throws SQLException {
        return adminService.areasOut(model);
    }

    @PostMapping("/adminSearch")
    public String adminSearch(RedirectAttributes attributes, @RequestParam(name = "search") String search
            , @RequestParam(name = "field") String field
            , @RequestParam(name = "fieldForSort") String fieldForSort
            , @RequestParam(name = "upDown") String upDown) throws SQLException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        return adminService.adminSearch(attributes,search,field,fieldForSort,upDown);
    }

    @PostMapping("/userSearch")
    public String userSearch(RedirectAttributes attributes, @RequestParam(name = "search") String search
            , @RequestParam(name = "field") String field
            , @RequestParam(name = "fieldForSort") String fieldForSort
            , @RequestParam(name = "upDown") String upDown) throws SQLException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
       return adminService.userSearch(attributes,search,field,fieldForSort,upDown);
    }

    @PostMapping("/areaSearch")
    public String areaSearch(RedirectAttributes attributes, @RequestParam(name = "search") String search
            , @RequestParam(name = "field") String field
            , @RequestParam(name = "fieldForSort") String fieldForSort
            , @RequestParam(name = "upDown") String upDown) throws SQLException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        return adminService.areaSearch(attributes,search,field,fieldForSort,upDown);
    }

    @PostMapping("/templateSearch")
    public String templateSearch(RedirectAttributes attributes, @RequestParam(name = "search") String search
            , @RequestParam(name =  "field") String field
            , @RequestParam(name = "fieldForSort") String fieldForSort
            , @RequestParam(name = "upDown") String upDown) throws SQLException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        return adminService.templateSearch(attributes,search,field,fieldForSort,upDown);
    }

    @PostMapping("/serviceSearch")
    public String serviceSearch(RedirectAttributes attributes, @RequestParam(name = "search") String search
            , @RequestParam(name =  "field") String field
            , @RequestParam(name = "fieldForSort") String fieldForSort
            , @RequestParam(name = "upDown") String upDown) throws SQLException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        return adminService.serviceSearch(attributes,search,field,fieldForSort,upDown);
    }

    @PostMapping("/orderSearch")
    public String orderSearch(RedirectAttributes attributes, @RequestParam(name = "search") String search
            , @RequestParam(name =  "field") String field
            , @RequestParam(name = "fieldForSort") String fieldForSort
            , @RequestParam(name = "upDown") String upDown) throws SQLException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        return adminService.orderSearch(attributes,search,field,fieldForSort,upDown);
    }
}
