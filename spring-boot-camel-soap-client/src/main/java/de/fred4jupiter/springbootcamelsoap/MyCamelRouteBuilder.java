package de.fred4jupiter.springbootcamelsoap;

import org.apache.camel.spring.SpringRouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class MyCamelRouteBuilder extends SpringRouteBuilder {

	@Override
	public void configure() throws Exception {
		from("direct:countries").bean("countryCaller").to("mock:end");
	}

}
