package camelinaction;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.model.ProcessorDefinition;
import org.apache.camel.test.spring.CamelSpringTestSupport;

public abstract class CustomCamelSpringTestSupport extends CamelSpringTestSupport {

	@Override
	public boolean isUseDebugger() {
		return false;
	}

	@Override
	protected void debugBefore(Exchange exchange, Processor processor, ProcessorDefinition<?> definition, String id,
			String shortName) {
		log.info("MyDebugger: before " + definition + " with body " + exchange.getIn().getBody());
	}

	@Override
	protected void debugAfter(Exchange exchange, Processor processor, ProcessorDefinition<?> definition, String id,
			String label, long timeTaken) {
		log.info("MyDebugger: after " + definition + " took " + timeTaken + " ms,		 with body "
				+ exchange.getIn().getBody());
	}

}
