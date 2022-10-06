package com.telenet.telenet;

import com.telenet.telenet.models.template.Template;
import com.telenet.telenet.models.template.impl.TemplateImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@SpringBootApplication
public class TelenetApplication {

	public static void main(String[] args) {
		SpringApplication.run(TelenetApplication.class, args);
		ApplicationContext context = new AnnotationConfigApplicationContext("com.telenet.telenet.models");
		Template template = context.getBean(TemplateImpl.class);
		System.out.println(template.getArea());
	}

}
