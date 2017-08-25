package com.camelinaction;

import org.apache.camel.spring.Main;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class StartApplication {

	public static void main(String[] args) throws Exception {
		Main main = new Main();
		main.setApplicationContext(new ClassPathXmlApplicationContext("META-INF/spring/camel-route.xml"));
		main.start();
	}
}
