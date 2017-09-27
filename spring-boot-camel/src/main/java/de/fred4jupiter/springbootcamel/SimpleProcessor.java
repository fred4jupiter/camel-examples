package de.fred4jupiter.springbootcamel;

import org.apache.camel.Handler;
import org.apache.camel.Header;
import org.apache.camel.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class SimpleProcessor {

	private static final Logger LOG = LoggerFactory.getLogger(SimpleProcessor.class);

	@Handler
	public void process(@Header("name") String name, Message message) throws Exception {
		LOG.debug("message: {}", message);

		if (name != null) {
			message.setBody("Hello " + name);
		} else {

			message.setBody("Hello World");
		}
	}

}
