package com.example.demo;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;

import com.example.demo.Model.TsscAdmin;
import com.example.demo.Model.TsscGame;
import com.example.demo.Model.TsscStory;
import com.example.demo.Model.TsscTopic;
import com.example.demo.Service.AdminService;
import com.example.demo.Service.GameService;
import com.example.demo.Service.StoryService;
import com.example.demo.Service.TopicService;



@SpringBootApplication
public class MiguelRomero {

	@Bean
	public Java8TimeDialect java8TimeDialect() {
		return new Java8TimeDialect();
	}
	
	public static void main(String[] args) {
		//SpringApplication.run(MiguelRomero.class, args);
		
		ConfigurableApplicationContext principal = SpringApplication.run(MiguelRomero.class, args);
		AdminService usuario = principal.getBean(AdminService.class);
		
		TsscAdmin nuevo= new TsscAdmin();
		nuevo.setPassword("{noop}123456");
		nuevo.setUsername("admin");
		nuevo.setSuperAdmin("admin");
		
		usuario.agregar(nuevo);
		
		TsscAdmin superAdmin= new TsscAdmin();
		superAdmin.setPassword("{noop}123456");
		superAdmin.setUsername("superAdmin");
		superAdmin.setSuperAdmin("superadmin");
		
		usuario.agregar(superAdmin);
		
		TopicService topicService = principal.getBean(TopicService.class);
		GameService gameService = principal.getBean(GameService.class);

		
		TsscTopic temaOne = new TsscTopic();
		temaOne.setName("Guerras 24");
		temaOne.setDescription("Guerra mundial");
		temaOne.setDefaultGroups(12);
		temaOne.setDefaultSprints(20);
		temaOne.setGroupPrefix("RV");
		temaOne.setId((long) 5);
		
		
		TsscTopic temaTwo = new TsscTopic();
		temaTwo.setName("Pandemia Covid 19");
		temaTwo.setDescription("Historia ddel mundo");
		temaTwo.setDefaultGroups(20);
		temaTwo.setDefaultSprints(20);
		temaTwo.setGroupPrefix("RR");
		temaTwo.setId((long) 4);
		
		TsscTopic temaThree = new TsscTopic();
		temaThree.setName("Final 48");
		temaThree.setDescription("Historia");
		temaThree.setDefaultGroups(30);
		temaThree.setDefaultSprints(30);
		temaThree.setGroupPrefix("RC");
		temaThree.setId((long) 3);
		
		
		topicService.AnadirTopic(temaOne);
		topicService.AnadirTopic(temaTwo);
	
		


		
	
		
	}

}
