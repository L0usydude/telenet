package com.telenet.telenet;

import com.telenet.telenet.models.area.Area;
import com.telenet.telenet.models.order.Order;
import com.telenet.telenet.models.service.Service;
import com.telenet.telenet.models.template.Template;
import com.telenet.telenet.models.user.User;
import com.telenet.telenet.utils.database.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.SQLException;
import java.util.List;

@SpringBootApplication
public class TelenetApplication {

	public static void main(String[] args) {
		SpringApplication.run(TelenetApplication.class, args);
//		UserDEFAULT_USERBase test = new UserDEFAULT_USERBase();
//		UserADMINBase test1 = new UserADMINBase();
//		AreaBase test2 = new AreaBase();
//		TemplateBase test3 = new TemplateBase();
//		ServiceBase test4 = new ServiceBase();
//		OrderBase test5 = new OrderBase();
//		try {
//			List<User> list =  test.getListOfUsers();
//			List<User> list1 = test1.getListOfUsers();
//			List<Area> list2 = test2.getListOfAreas();
//			List<Template> list3 = test3.getListOfTemplates();
//			List<Service> list4 = test4.getListOfServices();
//			List<Order> list5 = test5.getListOfOrders();
//			System.out.println(list2);
//			System.out.println(list5);
//			System.out.println(list);
//			System.out.println(list1);
//			System.out.println(list3);
//			System.out.println(list4);
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		ConfigurableApplicationContext context = new AnnotationConfigApplicationContext("com.telenet.telenet");
//		Runtime.getRuntime().addShutdownHook(new Thread() {
//			public void run() {
//				context.close();
//			}});
//		Storage storage1 = context.getBean(Storage.class);
//
//		storage1.getService(0).setUser(storage1.getUserImplByLogin("user1"));
//		System.out.println(storage1.getArea(1));
//		User admin1 = new User(1,"admin1", "adminLogin1", "adminPasswd1", RoleEnum.ADMIN);
//		Template template1 = new Template(0,"tempName1", "tempDescription1", 150,storage1.getArea(0));
//		Service service1 = new Service(0,"serviceName", "servideDescription", 200, template1, admin1, StatusEnum.ACTIVE);
//		storage1.addOrder(new Order(0,admin1,service1, StatusEnum.ACTIVE, ActionEnum.CONNECT));
//		storage1.addTemplate(template1);
//		storage1.addService(service1);
//		storage1.addUserAdmin(admin1);
//		User defUser = new User(1, "defUser1", "defLogin1", "defPasswd1", RoleEnum.DEFAULT_USER);
//		storage1.addUserImpl(defUser);
//		try {
//			JsonMapper.serialize(storage1.getServiceMap(), Service.class);
//
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		System.out.println(storage1.getService(0));
	}

}
