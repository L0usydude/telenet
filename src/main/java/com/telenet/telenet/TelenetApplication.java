package com.telenet.telenet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.telenet.telenet.models.area.Area;
import com.telenet.telenet.models.order.Order;
import com.telenet.telenet.models.service.Service;
import com.telenet.telenet.models.template.Template;
import com.telenet.telenet.models.user.User;
import com.telenet.telenet.utils.JsonMapper;
import com.telenet.telenet.utils.Storage;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.*;

//@SpringBootApplication
public class TelenetApplication {

	public static void main(String[] args) {
//		SpringApplication.run(TelenetApplication.class, args);
		ApplicationContext context = new AnnotationConfigApplicationContext("com.telenet.telenet");
		Storage storage1 = context.getBean(Storage.class);
		Template template = context.getBean(Template.class);

		User user1 = new User() ;
		User admin1 = new User();
		Service service1 = new Service();
		Template template1 = new Template();
		Order order1 = new Order();
		Area area1 = new Area();
		Area area2 = new Area(0, "ывфыв" , "sdad");

//		storage1.addArea(area1);
		storage1.addOrder(order1);
		storage1.addService(service1);
		storage1.addTemplate(template1);
		storage1.addUserAdmin(admin1);
		storage1.addUserImpl(user1);
//		storage1.addArea(area2);
		System.out.println(storage1.getArea(0));

		ObjectMapper objmapper = new ObjectMapper();
		try {
			objmapper.writeValue(new File("src/main/resources/dataBase/Area"), area2);
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			Area area3 = objmapper.readValue(new File("src/main/resources/dataBase/Area"), Area.class);
			System.out.println(area3);
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			System.out.println(JsonMapper.deserialize(Area.class));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

}
