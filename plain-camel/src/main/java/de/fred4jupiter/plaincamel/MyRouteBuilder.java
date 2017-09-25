package de.fred4jupiter.plaincamel;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;

/**
 * Created by michael on 12.09.2017.
 */
public class MyRouteBuilder extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		from("file://target/in?autoCreate=true").log("This is the body: ${body}").to("direct:inbox");

		from("direct:inbox").bean(BodyProcessor.class).wireTap("jms:queue:backup").to("file://target/out");

		from("jms:queue:backup")
				.setHeader(Exchange.FILE_NAME, simple("${file:name.noext}-${date:now:yyyyMMddHHmmssSSS}.${file:ext}"))
				.to("file://target/backup");
	}
}
