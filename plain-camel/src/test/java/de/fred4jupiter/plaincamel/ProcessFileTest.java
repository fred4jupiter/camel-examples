package de.fred4jupiter.plaincamel;

import java.io.File;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.builder.NotifyBuilder;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.apache.commons.io.FileUtils;
import org.junit.Test;

public class ProcessFileTest extends CamelTestSupport {

	public void setUp() throws Exception {
		super.setUp();
		deleteDirectory("target/in");
		deleteDirectory("target/out");
	}

	@Override
	protected CamelContext createCamelContext() throws Exception {
		CamelContext context = new DefaultCamelContext();
		context.addComponent("jms", ComponentCreator.createJmsComponent());
		return context;
	}

	@Override
	protected RouteBuilder createRouteBuilder() throws Exception {
		return new MyRouteBuilder();
	}

	@Test
	public void moveFileAndCheckContent() throws Exception {
		final String filename = "hello.txt";

		template.sendBodyAndHeader("file://target/in", "Hello World", Exchange.FILE_NAME, filename);
		Thread.sleep(2000);
		File target = new File("target/out/" + filename);
		assertTrue("File not moved", target.exists());

		String fileContent = FileUtils.readFileToString(target);
		assertEquals("Hello World, filename: hello.txt", fileContent);
	}

	@Test
	public void moveFileUsingNotifyBuilder() throws Exception {
		final String filename = "content.txt";

		NotifyBuilder notify = new NotifyBuilder(context).whenDone(1).create();
		template.sendBodyAndHeader("file://target/in", "This is a file content", Exchange.FILE_NAME, filename);
		assertTrue(notify.matchesMockWaitTime());

		File target = new File("target/out/" + filename);
		assertTrue("File not moved", target.exists());
		String content = context.getTypeConverter().convertTo(String.class, target);
		assertEquals("This is a file content, filename: content.txt", content);
	}
}
