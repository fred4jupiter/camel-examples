package com.camelinaction;

import org.apache.camel.CamelContext;
import org.apache.camel.component.cxf.CxfEndpoint;
import org.apache.camel.spring.SpringCamelContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import camelinaction.order.OrderEndpoint;

@Configuration
@ComponentScan("com.camelinaction")
// @ImportResource({ "classpath:META-INF/cxf/cxf.xml" })
public class CamelCxfConfig {

	// @Autowired
	// private Bus cxfBus;

	@Bean
	public CamelContext camelContext(ApplicationContext applicationContext, CxfRouteBuilder cxfRouteBuilder)
			throws Exception {
		SpringCamelContext context = new SpringCamelContext(applicationContext);
		context.addRoutes(cxfRouteBuilder);
		return context;
	}

	@Bean
	public CxfEndpoint orderEndpoint(CamelContext camelContext) {
		CxfEndpoint endpoint = new CxfEndpoint();
		endpoint.setAddress("http://localhost:9000/order/");
		endpoint.setServiceClass(OrderEndpoint.class);
		endpoint.setWsdlURL("wsdl/order.wsdl");
		endpoint.setCamelContext(camelContext);
		return endpoint;
	}

	@Bean
	public CxfRouteBuilder cxfRouteBuilder() {
		return new CxfRouteBuilder();
	}
}
