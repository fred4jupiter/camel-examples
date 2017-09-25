package de.fred4jupiter.plaincamel;

import org.apache.camel.CamelContext;
import org.apache.camel.main.Main;

/**
 * Created by michael on 12.09.2017.
 */
public class Application extends org.apache.camel.main.Main {

	public Application() throws Exception {
		super();
		getOrCreateCamelContext().addRoutes(new MyRouteBuilder());
	}

	@Override
	protected CamelContext createContext() {
		CamelContext camelContext = CamelContextCreator.createCamelContext();
		return camelContext;
	}

	public static void main(String[] args) throws Exception {

		Application application = new Application();
		application.run();

	}
}
