package com.camelinaction;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;


@Component
public class CxfRouteBuilder extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		// expose web service

		from("cxf:bean:orderEndpoint")
                .transform().simple("${body[0]}")
                .log("Log1: Incoming Order body: ${body.partName} ${body.amount} ${body.customerName}")
                .transform(constant("OK"));



	}

}
