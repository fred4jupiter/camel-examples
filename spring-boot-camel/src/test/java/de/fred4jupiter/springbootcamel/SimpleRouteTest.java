package de.fred4jupiter.springbootcamel;

import static org.junit.Assert.assertNotNull;

import org.apache.camel.EndpointInject;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.spring.CamelSpringBootRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@RunWith(CamelSpringBootRunner.class)
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class SimpleRouteTest {

	@EndpointInject(uri = "{{simpleRoute.start}}")
	private ProducerTemplate simpleRouteStartEndpoint;

	@EndpointInject(uri = "{{simpleRoute.end}}")
	private MockEndpoint resultEndpoint;

	@Before
	public void checkInjection() {
		assertNotNull(simpleRouteStartEndpoint);
	}

	@Test
	public void triggerRouteWithoutHeader() throws Exception {
		resultEndpoint.expectedMessageCount(1);
		resultEndpoint.allMessages().body().isEqualTo("Hello World");

		simpleRouteStartEndpoint.sendBody("");

		resultEndpoint.assertIsSatisfied();
	}

	@Test
	public void triggerRouteWithHeader() throws Exception {
		resultEndpoint.expectedMessageCount(1);
		resultEndpoint.allMessages().body().isEqualTo("Hello Michael");

		simpleRouteStartEndpoint.sendBodyAndHeader("", "name", "Michael");

		resultEndpoint.assertIsSatisfied();
	}
}
