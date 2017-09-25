package de.fred4jupiter.plaincamel;

import java.io.File;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.model.ProcessorDefinition;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.apache.commons.io.FileUtils;
import org.junit.Test;

public class ProcessFileTest extends CamelTestSupport {

	@Override
	public boolean isUseDebugger() {
		return true;
	}

	@Override
	protected void debugBefore(Exchange exchange, Processor processor, ProcessorDefinition<?> definition, String id,
			String shortName) {
		log.info("MyDebugger: before " + definition + " with body " + exchange.getIn().getBody());
	}

	@Override
	protected void debugAfter(Exchange exchange, Processor processor, ProcessorDefinition<?> definition, String id,
			String label, long timeTaken) {
		log.info("MyDebugger: after " + definition + " took " + timeTaken + " ms,	 with body "
				+ exchange.getIn().getBody());
	}

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
		template.sendBodyAndHeader("file://target/in", "Hello World", Exchange.FILE_NAME, "hello.txt");
		Thread.sleep(2000);
		File target = new File("target/out/hello.txt");
		assertTrue("File not moved", target.exists());

		String fileContent = FileUtils.readFileToString(target);
		assertEquals("Hello World, filename: hello.txt", fileContent);
	}
}
