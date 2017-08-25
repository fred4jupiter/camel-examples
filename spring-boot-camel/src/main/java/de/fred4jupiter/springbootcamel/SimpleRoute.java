package de.fred4jupiter.springbootcamel;

import org.apache.camel.spring.SpringRouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class SimpleRoute extends SpringRouteBuilder {

	@Override
	public void configure() throws Exception {
		from("{{simpleRoute.start}}").bean("simpleProcessor").to("{{simpleRoute.end}}");
	}

}
