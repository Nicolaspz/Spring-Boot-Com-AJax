package com.nicolau.demoAjax;


import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class DemoAjaxApplication implements CommandLineRunner {
	

	public static void main(String[] args) {
		SpringApplication.run(DemoAjaxApplication.class, args);
	}

	
	@Override
	public void run(String... args) throws Exception {
		
	//SocilaMetaTag org= service.getOpenGraphByUrl("https://www.nuuvem.com/ao-en/item/holy-potatoes-weapon-shop-dlc-bundle");
	//SocilaMetaTag org =	service.getOpenGraphByUrl("https://www.udemy.com/course/spring-boot-mvc-com-thymeleaf/");
	//	System.out.println(org.toString());
	}

}
