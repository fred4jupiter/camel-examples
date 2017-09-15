package de.fred4jupiter.plaincamel;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.camel.CamelContext;
import org.apache.camel.component.jms.JmsComponent;
import org.apache.camel.impl.DefaultCamelContext;

import javax.jms.ConnectionFactory;

/**
 * Created by michael on 12.09.2017.
 */
public class Application {

	public static void main(String[] args) throws Exception {
		CamelContext context = new DefaultCamelContext();
		context.addRoutes(new MyRouteBuilder());

		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("vm://localhost?broker.persistent=false");
		context.addComponent("jms", JmsComponent.jmsComponentAutoAcknowledge(connectionFactory));

		context.start();
		while (Thread.activeCount() > 1) {
			// blocking main thread
		}
		//        Thread.sleep(10000);
		//        context.stop();
	}
}
