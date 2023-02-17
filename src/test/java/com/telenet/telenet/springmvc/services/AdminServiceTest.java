package com.telenet.telenet.springmvc.services;

import com.fasterxml.jackson.datatype.jsr310.ser.YearSerializer;
import com.telenet.telenet.models.BaseEntity;
import com.telenet.telenet.models.area.Area;
import com.telenet.telenet.models.enums.action.ActionEnum;
import com.telenet.telenet.models.enums.roles.RoleEnum;
import com.telenet.telenet.models.enums.status.StatusEnum;
import com.telenet.telenet.models.order.Order;
import com.telenet.telenet.models.service.Service;
import com.telenet.telenet.models.template.Template;
import com.telenet.telenet.models.user.User;
import com.telenet.telenet.utils.database.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class AdminServiceTest {

    List<User> userList;
    List<User> adminList;
    List<Area> areaList;
    List<Order> orderList;
    List<Service> serviceList;
    List<Template> templateList;
    AdminService adminService;


    @BeforeEach
    public void initialize(){
        System.out.println("BeforeEach");
        adminService = new AdminService();
        userList = new ArrayList<>();
        userList.add(new User(1, "122121","i1323123","1|", RoleEnum.DEFAULT_USER));
        userList.add(new User(2, "3122121","j1323123","2|", RoleEnum.DEFAULT_USER));
        userList.add(new User(3, "5122121","h1323123","y|", RoleEnum.DEFAULT_USER));
        userList.add(new User(4, "8122121","g1323123","j|", RoleEnum.DEFAULT_USER));
        userList.add(new User(5, "9122121","a1323123","lll|", RoleEnum.DEFAULT_USER));
        adminList = new ArrayList<>();
        adminList.add(new User(1, "122121","i1323123","1|", RoleEnum.ADMIN));
        adminList.add(new User(2, "3122121","j1323123","2|", RoleEnum.ADMIN));
        adminList.add(new User(3, "5122121","h1323123","y|", RoleEnum.ADMIN));
        adminList.add(new User(4, "8122121","g1323123","j|", RoleEnum.ADMIN));
        adminList.add(new User(5, "9122121","a1323123","lll|", RoleEnum.ADMIN));
        areaList = new ArrayList<>();
        areaList.add(new Area(1,"111113123", "y213123"));
        areaList.add(new Area(2,"sadd13123", "d213123"));
        areaList.add(new Area(3,"FGDFG13123", "a213123"));
        areaList.add(new Area(4,"FDFsAS13123", "7213123"));
        areaList.add(new Area(5,"aS13123", "213123"));
        templateList = new ArrayList<>();
        templateList.add(new Template(1, "sdasd", "asdadgdfgdf", 2123123, new Area(4,"FDFsAS13123", "7213123") ));
        templateList.add(new Template(2, "1sdasd", "basdadgdfgdf", 5123123, new Area(3,"FGDFG13123", "a213123") ));
        templateList.add(new Template(3, "sassdasd", "casdadgdfgdf", 6123123, new Area(1,"111113123", "y213123") ));
        templateList.add(new Template(4, "kksdasd", "dasdadgdfgdf", 213123123, new Area(2,"sadd13123", "d213123") ));
        templateList.add(new Template(5, "uusdasd", "tasdadgdfgdf", 121313123, new Area(5,"aS13123", "213123") ));
        serviceList = new ArrayList<>();
        serviceList.add(new Service(1,"asdadsad", "asdasdasd", 12123
                , new Template(1, "sdasd", "asdadgdfgdf", 2123123, new Area(4,"FDFsAS13123", "7213123"))
                , new User(5, "9122121","a1323123","lll|", RoleEnum.DEFAULT_USER)
        , StatusEnum.ACTIVE));
        serviceList.add(new Service(2,"asdadsad", "asdasdasd", 12123
                , new Template(4, "kksdasd", "dasdadgdfgdf", 213123123, new Area(2,"sadd13123", "d213123") )
                , new User(4, "8122121","g1323123","j|", RoleEnum.DEFAULT_USER)
                , StatusEnum.ACTIVE));
        serviceList.add(new Service(3,"asdadsad", "asdasdasd", 12123
                , new Template(2, "1sdasd", "basdadgdfgdf", 5123123, new Area(3,"FGDFG13123", "a213123") )
                , new User(3, "5122121","h1323123","y|", RoleEnum.DEFAULT_USER)
                , StatusEnum.ACTIVE));
        serviceList.add(new Service(4,"asdadsad", "asdasdasd", 12123
                , new Template(3, "sassdasd", "casdadgdfgdf", 6123123, new Area(1,"111113123", "y213123") )
                , new User(2, "3122121","j1323123","2|", RoleEnum.DEFAULT_USER)
                , StatusEnum.ACTIVE));
        serviceList.add(new Service(5,"asdadsad", "asdasdasd", 12123
                , new Template(5, "uusdasd", "tasdadgdfgdf", 121313123, new Area(5,"aS13123", "213123") )
                , new User(1, "122121","i1323123","1|", RoleEnum.DEFAULT_USER)
                , StatusEnum.ACTIVE));
        orderList = new ArrayList<>();
        orderList.add(new Order(1
                , new User(1, "122121","i1323123","1|", RoleEnum.ADMIN)
                , new Service(1,"asdadsad", "asdasdasd", 12123
                , new Template(1, "sdasd", "asdadgdfgdf", 2123123, new Area(4,"FDFsAS13123", "7213123"))
                , new User(5, "9122121","a1323123","lll|", RoleEnum.DEFAULT_USER)
                , StatusEnum.ACTIVE)
                , StatusEnum.ACTIVE
                , ActionEnum.CONNECT));
        orderList.add(new Order(1
                , new User(4, "8122121","g1323123","j|", RoleEnum.DEFAULT_USER)
                , new Service(2,"asdadsad", "asdasdasd", 12123
                , new Template(4, "kksdasd", "dasdadgdfgdf", 213123123, new Area(2,"sadd13123", "d213123") )
                , new User(4, "8122121","g1323123","j|", RoleEnum.DEFAULT_USER)
                , StatusEnum.ACTIVE)
                , StatusEnum.ACTIVE
                , ActionEnum.CONNECT));
        orderList.add(new Order(1
                , new User(2, "3122121","j1323123","2|", RoleEnum.ADMIN)
                , new Service(3,"asdadsad", "asdasdasd", 12123
                , new Template(2, "1sdasd", "basdadgdfgdf", 5123123, new Area(3,"FGDFG13123", "a213123") )
                , new User(3, "5122121","h1323123","y|", RoleEnum.DEFAULT_USER)
                , StatusEnum.ACTIVE)
                , StatusEnum.ACTIVE
                , ActionEnum.CONNECT));
        orderList.add(new Order(1
                , new User(3, "5122121","h1323123","y|", RoleEnum.ADMIN)
                , new Service(4,"asdadsad", "asdasdasd", 12123
                , new Template(3, "sassdasd", "casdadgdfgdf", 6123123, new Area(1,"111113123", "y213123") )
                , new User(2, "3122121","j1323123","2|", RoleEnum.DEFAULT_USER)
                , StatusEnum.ACTIVE)
                , StatusEnum.ACTIVE
                , ActionEnum.CONNECT));
        orderList.add(new Order(1
                , new User(5, "9122121","a1323123","lll|", RoleEnum.DEFAULT_USER)
                , new Service(5,"asdadsad", "asdasdasd", 12123
                , new Template(5, "uusdasd", "tasdadgdfgdf", 121313123, new Area(5,"aS13123", "213123") )
                , new User(1, "122121","i1323123","1|", RoleEnum.DEFAULT_USER)
                , StatusEnum.ACTIVE)
                , StatusEnum.ACTIVE
                , ActionEnum.CONNECT));
    }
    @AfterEach()
    public void delete(){
        System.out.println("AfterEach");
        userList = null;
        adminService = null;
        serviceList = null;
        orderList = null;
        adminList = null;
        templateList = null;
        areaList = null;
    }



    @Test
    public void sortIdTest_User(){
        System.out.println(1);
        Assertions.assertEquals(adminService.sortId(userList, "up"), userList.stream().sorted(Comparator.comparingInt(User::getId)).toList());
        Assertions.assertEquals(adminService.sortId(userList, "down"), userList.stream().sorted((o1, o2) -> o2.getId() - o1.getId()).toList());
    }
    @Test
    public void sortIdTest_Admin(){
        System.out.println(2);
        Assertions.assertEquals(adminService.sortId(adminList,"up"), adminList.stream().sorted(Comparator.comparingInt(User::getId)).toList());
        Assertions.assertEquals(adminService.sortId(adminList,"down"), adminList.stream().sorted((o1, o2) -> o2.getId() - o1.getId()).toList());
    }
    @Test
    public void sortIdTest_Area(){
        System.out.println(3);
        Assertions.assertEquals(adminService.sortId(areaList,"up"), areaList.stream().sorted(Comparator.comparingInt(BaseEntity::getId)).toList());
        Assertions.assertEquals(adminService.sortId(areaList,"down"), areaList.stream().sorted((o1, o2) -> o2.getId() - o1.getId()).toList());
    }
    @Test
    public void sortIdTest_Template(){
        System.out.println(4);
        Assertions.assertEquals(adminService.sortId(templateList,"up"), templateList.stream().sorted(Comparator.comparingInt(BaseEntity::getId)).toList());
        Assertions.assertEquals(adminService.sortId(templateList,"down"), templateList.stream().sorted((o1, o2) -> o2.getId() - o1.getId()).toList());
    }
    @Test
    public void sortIdTest_Service(){
        System.out.println(5);
        Assertions.assertEquals(adminService.sortId(serviceList,"up"), serviceList.stream().sorted(Comparator.comparingInt(BaseEntity::getId)).toList());
        Assertions.assertEquals(adminService.sortId(serviceList,"down"), serviceList.stream().sorted((o1, o2) -> o2.getId() - o1.getId()).toList());
    }
    @Test
    public void sortIdTest_Order(){
        System.out.println(6);
        Assertions.assertEquals(adminService.sortId(orderList,"up"), orderList.stream().sorted(Comparator.comparingInt(BaseEntity::getId)).toList());
        Assertions.assertEquals(adminService.sortId(orderList,"down"), orderList.stream().sorted((o1, o2) -> o2.getId() - o1.getId()).toList());
    }

}
