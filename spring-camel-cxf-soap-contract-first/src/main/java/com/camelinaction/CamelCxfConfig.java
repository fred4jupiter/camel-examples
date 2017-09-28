package com.camelinaction;

import org.apache.camel.CamelContext;
import org.apache.camel.component.cxf.CxfEndpoint;
import org.apache.camel.spring.SpringCamelContext;
import org.apache.cxf.bus.spring.SpringBus;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import camelinaction.order.OrderEndpoint;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ComponentScan
public class CamelCxfConfig {

	@Bean
	public CamelContext camelContext(ApplicationContext applicationContext, CxfRouteBuilder cxfRouteBuilder)
			throws Exception {
		SpringCamelContext context = new SpringCamelContext(applicationContext);
		context.addRoutes(cxfRouteBuilder);
		return context;
	}

	@Bean
	public SpringBus cxf() {
		return new SpringBus();
	}

	// cxf:bean:orderEndpoint
	@Bean
	public CxfEndpoint orderEndpoint(CamelContext camelContext) {
		CxfEndpoint endpoint = new CxfEndpoint();
		endpoint.setAddress("/order");
		endpoint.setServiceClass(OrderEndpoint.class);
		endpoint.setWsdlURL("wsdl/order.wsdl");
		endpoint.setCamelContext(camelContext);
		return endpoint;
	}

}
