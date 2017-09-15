package com.camelinaction;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class CxfRouteBuilder extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		from("cxf:bean:orderEndpoint").to("seda:incomingOrders").transform(constant("OK"));

		from("seda:incomingOrders").log("Got ${body}").to("mock:end");
	}

}