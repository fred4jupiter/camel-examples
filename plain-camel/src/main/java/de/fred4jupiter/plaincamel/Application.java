package de.fred4jupiter.plaincamel;

import org.apache.camel.main.Main;

/**
 * Created by michael on 12.09.2017.
 */
public class Application {

	public static void main(String[] args) throws Exception {
		Main main = new Main();

		main.bind("jms", ComponentCreator.createJmsComponent());
		main.addRouteBuilder(new MyRouteBuilder());

		main.run();
	}
}
