package com.sebek;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sebek.model.Test;
import com.sebek.service.TestService;


public class TestApp {


	public static void main(String[] args) {
		
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("test.xml");
		TestService testService = applicationContext.getBean("testService", TestService.class);

		Test test = new Test();
		test.setId(1);
		test.setTest("test");
		testService.persist(test);
		
		System.out.println("Done.");
		
	}

}
