package com.camelinaction;

import camelinaction.order.Order;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.cxf.DataFormat;
import org.apache.camel.dataformat.soap.name.QNameStrategy;
import org.apache.camel.dataformat.soap.name.ServiceInterfaceStrategy;
import org.apache.camel.dataformat.soap.name.TypeNameStrategy;
import org.apache.camel.model.DataFormatDefinition;
import org.apache.camel.model.dataformat.JaxbDataFormat;
import org.apache.camel.model.dataformat.SoapJaxbDataFormat;
import org.springframework.stereotype.Component;

import javax.xml.namespace.QName;

@Component
public class CxfRouteBuilder extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		// expose web service / consumer
        getContext().setTracing(false);

		from("cxf:bean:orderEndpoint")
                .transform().simple("${body[0]}")
                .log("Log1: Incoming Order body: ${body.partName} ${body.amount} ${body.customerName}")
                .to("seda:incomingOrders")
                .transform(constant("OK"));

		from("seda:incomingOrders")
				.log("Log2 Incoming Order  body: ${body}")
				.to("mock:end");

		// Producer calls remote web service (calling web service from within the route)
		from("direct:callWebService")
				.log("before calling web service. body: ${body.partName} ${body.amount} ${body.customerName}")
				.to("cxf:bean:orderEndpoint").log("called web service with result: ${body}").to("mock:end2");
	}

}
