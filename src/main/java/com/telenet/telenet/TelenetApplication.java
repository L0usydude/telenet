package com.telenet.telenet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.telenet.telenet.models.area.impl.AreaImpl;
import com.telenet.telenet.models.order.impl.OrderImpl;
import com.telenet.telenet.models.service.impl.ServiceImpl;
import com.telenet.telenet.models.template.Template;
import com.telenet.telenet.models.template.impl.TemplateImpl;
import com.telenet.telenet.models.user.impl.UserAdmin;
import com.telenet.telenet.models.user.impl.UserImpl;
import com.telenet.telenet.utils.Storage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.File;
import java.io.IOException;

@SpringBootApplication
public class TelenetApplication {

	public static void main(String[] args) {
//		SpringApplication.run(TelenetApplication.class, args);
		ApplicationContext context = new AnnotationConfigApplicationContext("com.telenet.telenet");
		Storage storage1 = context.getBean(Storage.class);
		Template template = context.getBean(TemplateImpl.class);

		UserImpl user1 = new UserImpl() ;
		UserAdmin admin1 = new UserAdmin();
		ServiceImpl service1 = new ServiceImpl();
		TemplateImpl template1 = new TemplateImpl();
		OrderImpl order1 = new OrderImpl();
		AreaImpl area1 = new AreaImpl();
		AreaImpl area2 = new AreaImpl(0, "ывфыв" , "sdad");

		storage1.addArea(area1);
		storage1.addOrder(order1);
		storage1.addService(service1);
		storage1.addTemplate(template1);
		storage1.addUserAdmin(admin1);
		storage1.addUserImpl(user1);
		storage1.addArea(area2);
		System.out.println(storage1.getArea(0));

		ObjectMapper objmapper = new ObjectMapper();
		try {
			objmapper.writeValue(new File("src/main/resources/dataBase/Areas"), area2);
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			AreaImpl area3 = objmapper.readValue(new File("src/main/resources/dataBase/Areas"), AreaImpl.class);
			System.out.println(area3);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
