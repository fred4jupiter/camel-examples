package com.camelinaction;

import org.apache.camel.spring.Main;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class StartApplication {

	public static void main(String[] args) throws Exception {
		Main main = new Main();
		main.setApplicationContext(new AnnotationConfigApplicationContext(CamelCxfConfig.class));
		main.start();
	}
}
