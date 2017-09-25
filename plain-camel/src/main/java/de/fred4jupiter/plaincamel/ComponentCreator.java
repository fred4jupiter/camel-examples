package de.fred4jupiter.plaincamel;

import javax.jms.ConnectionFactory;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.camel.component.jms.JmsComponent;

public class ComponentCreator {

	public static JmsComponent createJmsComponent() {
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("vm://localhost?broker.persistent=false");
		return JmsComponent.jmsComponentAutoAcknowledge(connectionFactory);
	}
}
