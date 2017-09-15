package camelinaction;

import java.util.ArrayList;
import java.util.List;

import org.apache.camel.test.spring.CamelSpringTestSupport;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import com.camelinaction.CamelCxfConfig;

import camelinaction.order.Order;

public class OrderTest extends CamelSpringTestSupport {

	@Override
	protected AbstractApplicationContext createApplicationContext() {
		return new AnnotationConfigApplicationContext(CamelCxfConfig.class);
	}

	@Test
	public void testOrderOk() throws Exception {
		List<Object> params = new ArrayList<Object>();
		params.add("motor");
		params.add(1);
		params.add("honda");

		Order order = new Order();
		order.setPartName("motor");
		order.setAmount(1);
		order.setCustomerName("honda");

		// sends the order to the CXF endpoint (webservice)
		String reply = template.requestBody("cxf:bean:orderEndpoint", order, String.class);
		assertEquals("OK", reply);
	}

}
