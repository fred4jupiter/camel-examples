package camelinaction;

import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.spring.CamelSpringTestSupport;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import com.camelinaction.CamelCxfConfig;

import camelinaction.order.Order;

/*
 * Started den camel context und damit auch die Route.
 */
public class OrderTest extends CamelSpringTestSupport {

	// @Override
	// public boolean isUseDebugger() {
	// return true;
	// }
	//
	// @Override
	// protected void debugBefore(Exchange exchange, Processor processor,
	// ProcessorDefinition<?> definition, String id,
	// String shortName) {
	// log.info("MyDebugger: before " + definition + " with body " +
	// exchange.getIn().getBody());
	// }
	//
	// @Override
	// protected void debugAfter(Exchange exchange, Processor processor,
	// ProcessorDefinition<?> definition, String id,
	// String label, long timeTaken) {
	// log.info("MyDebugger: after " + definition + " took " + timeTaken + " ms,
	// with body "
	// + exchange.getIn().getBody());
	// }

	@Override
	protected AbstractApplicationContext createApplicationContext() {
		return new AnnotationConfigApplicationContext(CamelCxfConfig.class);
	}

	@Test
	public void testOrderOk() throws Exception {
		Order order = new Order();
		order.setPartName("motor");
		order.setAmount(1);
		order.setCustomerName("honda");

		MockEndpoint mockEndpoint = getMockEndpoint("mock:end");
		mockEndpoint.expectedMessageCount(1);

		// calls the web service directly (no route processing)
		String reply = template.requestBody("cxf:bean:orderEndpoint", order, String.class);
		assertEquals("OK", reply);

		mockEndpoint.assertIsSatisfied();
	}

}
