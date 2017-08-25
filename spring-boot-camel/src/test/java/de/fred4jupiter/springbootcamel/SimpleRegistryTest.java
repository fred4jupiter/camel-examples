package de.fred4jupiter.springbootcamel;

import static org.junit.Assert.assertEquals;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.impl.SimpleRegistry;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SimpleRegistryTest {

	private CamelContext context;

	private ProducerTemplate template;

	@Before
	public void setUp() throws Exception {
		SimpleRegistry registry = new SimpleRegistry();
		registry.put("simpleProcessor", new SimpleProcessor());
		context = new DefaultCamelContext(registry);
		template = context.createProducerTemplate();
		template.setDefaultEndpointUri("direct:hello");
		context.addRoutes(new RouteBuilder() {
			public void configure() throws Exception {
				from("direct:hello").bean("simpleProcessor");
			}
		});
		context.start();
	}

	@After
	public void tearDown() throws Exception {
		template.stop();
		context.stop();
	}

	@Test
	public void callRouteWithName() throws Exception {
		Object reply = template.requestBodyAndHeader("", "name", "Michael");
		assertEquals("Hello Michael", reply);
	}
}
