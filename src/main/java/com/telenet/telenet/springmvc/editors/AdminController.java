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
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
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
        model.addAttribute("fields", getClassFields(User.class));
        return "modelsLists/admins";
    }
    @GetMapping("/users")
    public String usersOut(Model model) throws SQLException {
        if (!model.containsAttribute("usersCollection")){
            model.addAttribute("usersCollection", userDEFAULT_userBase.getListOfUsers());
        }
        model.addAttribute("fields", getClassFields(User.class));
        return "modelsLists/users";
    }
    @GetMapping("/templates")
    public String templatesOut(Model model) throws SQLException {
        if (!model.containsAttribute("templatesCollection")){
            model.addAttribute("templatesCollection", templateBase.getListOfTemplates());
        }
        model.addAttribute("fields", getClassFields(Template.class));
        return "modelsLists/templates";
    }
    @GetMapping("/services")
    public String servicesOut(Model model) throws SQLException {
        if (!model.containsAttribute("servicesCollection")) {
            model.addAttribute("servicesCollection", serviceBase.getListOfServices());
        }
        model.addAttribute("fields", getClassFields(Service.class));
        return "modelsLists/services";
    }
    @GetMapping("/orders")
    public String ordersOut(Model model) throws SQLException {
        if (!model.containsAttribute("ordersCollection")) {
            model.addAttribute("ordersCollection", orderBase.getListOfOrders());}
        model.addAttribute("fields", getClassFields(Order.class));
        return "modelsLists/orders";
    }
    @GetMapping("/areas")
    public String areasOut(Model model) throws SQLException {
        if(!model.containsAttribute("areasCollection")){
            model.addAttribute("areasCollection", areaBase.getListOfAreas());
        }
        model.addAttribute("fields", getClassFields(Area.class));
        return "modelsLists/areas";
    }

    @PostMapping("/adminSearch")
    public String adminSearch(RedirectAttributes attributes, @RequestParam(name = "search") String search
            , @RequestParam(name = "field") String field) throws SQLException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method method = userADMINBase.getClass().getMethod(field + "Search", String.class);
        List<User> list = (List<User>) method.invoke(userADMINBase,search);
        attributes.addFlashAttribute("adminsCollection", list);
        return "redirect:/admin/admins";
    }

    @PostMapping("/userSearch")
    public String userSearch(RedirectAttributes attributes, @RequestParam(name = "search") String search
            , @RequestParam(name = "field") String field) throws SQLException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method method = userDEFAULT_userBase.getClass().getMethod(field + "Search", String.class);
        List<User> list = (List<User>) method.invoke(userDEFAULT_userBase, search);
        attributes.addFlashAttribute("usersCollection", list);
        return "redirect:/admin/users";
    }

    @PostMapping("/areaSearch")
    public String areaSearch(RedirectAttributes attributes, @RequestParam(name = "search") String search
            , @RequestParam(name = "field") String field) throws SQLException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method method = areaBase.getClass().getMethod(field + "Search", String.class);
        List<Area> list = (List<Area>) method.invoke(areaBase, search);
        attributes.addFlashAttribute("areasCollection",list);
        return "redirect:/admin/areas";
    }

    @PostMapping("/templateSearch")
    public String templateSearch(RedirectAttributes attributes, @RequestParam(name = "search") String search
            , @RequestParam(name =  "field") String field) throws SQLException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method method = templateBase.getClass().getMethod(field + "Search", String.class);
        List<Template> list = (List<Template>) method.invoke(templateBase,search);
        attributes.addFlashAttribute("templatesCollection", list);
        return "redirect:/admin/templates";
    }

    @PostMapping("/serviceSearch")
    public String serviceSearch(RedirectAttributes attributes, @RequestParam(name = "search") String search
            , @RequestParam(name =  "field") String field) throws SQLException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method method = serviceBase.getClass().getMethod(field + "Search", String.class);
        List<Service> list = (List<Service>) method.invoke(serviceBase,search);
        attributes.addFlashAttribute("servicesCollection", list);
        return "redirect:/admin/services";
    }

    @PostMapping("/orderSearch")
    public String orderSearch(RedirectAttributes attributes, @RequestParam(name = "search") String search
            , @RequestParam(name =  "field") String field) throws SQLException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method method = orderBase.getClass().getMethod(field + "Search", String.class);
        List<Order> list = (List<Order>) method.invoke(orderBase,search);
        attributes.addFlashAttribute("ordersCollection", list);
        return "redirect:/admin/orders";
    }
}
