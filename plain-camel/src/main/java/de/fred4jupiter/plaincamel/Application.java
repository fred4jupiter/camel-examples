package de.fred4jupiter.plaincamel;

import org.apache.camel.CamelContext;

/**
 * Created by michael on 12.09.2017.
 */
public class Application {

	public static void main(String[] args) throws Exception {
		CamelContext camelContext = CamelContextCreator.createCamelContext();

		camelContext.addRoutes(new MyRouteBuilder());

		camelContext.start();
		while (Thread.activeCount() > 1) {
			// blocking main thread
		}
	}
}
