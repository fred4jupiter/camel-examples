package de.fred4jupiter.springbootcamel;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class SimpleProcessor implements Processor {

	private static final Logger LOG = LoggerFactory.getLogger(SimpleProcessor.class);

	public SimpleProcessor() {
		// System.err.println("Created class: " + Thread.currentThread().getId());
	}

	@Override
	public void process(Exchange exchange) throws Exception {
		LOG.debug("exchange: {}", exchange);

		String name = exchange.getIn().getHeader("name", String.class);

		if (name != null) {
			exchange.getIn().setBody("Hello " + name);
		} else {
			exchange.getIn().setBody("Hello World");
		}
	}

}
