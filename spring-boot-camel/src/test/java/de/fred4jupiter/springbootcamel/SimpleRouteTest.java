package de.fred4jupiter.springbootcamel;

import static org.junit.Assert.assertNotNull;

import org.apache.camel.CamelContext;
import org.apache.camel.EndpointInject;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.spring.CamelSpringBootRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@RunWith(CamelSpringBootRunner.class)
@SpringBootTest
public class SimpleRouteTest {

	@Autowired
	private CamelContext camelContext;

	@EndpointInject(uri = "{{simpleRoute.start}}")
	private ProducerTemplate simpleRouteStartEndpoint;

	@EndpointInject(uri = "{{simpleRoute.end}}")
	private MockEndpoint resultEndpoint;

	@Test
	public void shouldSucceed() throws Exception {
		assertNotNull(camelContext);
		assertNotNull(simpleRouteStartEndpoint);

		simpleRouteStartEndpoint.sendBody("");

		resultEndpoint.expectedMessageCount(1);
		resultEndpoint.allMessages().body().isEqualTo("Hello World");
		resultEndpoint.assertIsSatisfied();
	}
}
