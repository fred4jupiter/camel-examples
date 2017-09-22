package com.camelinaction;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class CxfRouteBuilder extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		// expose web service
		from("cxf:bean:orderEndpoint").to("seda:incomingOrders").transform(constant("OK"));

		from("seda:incomingOrders").log("Got body: ${body}").to("mock:end");

		// Producer calls remote web service (calling web service from within the route)
		from("direct:callWebService")
				.log("before calling web service. body: ${body.partName} ${body.amount} ${body.customerName}")
				.to("cxf:bean:orderEndpoint").log("called web service with result: ${body}").to("mock:end");
	}

}
