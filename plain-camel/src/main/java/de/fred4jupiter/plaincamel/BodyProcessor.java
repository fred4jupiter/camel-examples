package de.fred4jupiter.plaincamel;

import org.apache.camel.Body;
import org.apache.camel.Handler;
import org.apache.camel.Header;
import org.apache.camel.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by michael on 12.09.2017.
 */
public class BodyProcessor {

	private static final Logger LOG = LoggerFactory.getLogger(BodyProcessor.class);

	@Handler
	public void enrichBody(@Header("CamelFileName") String fileName, @Body String body, Message message) {
		LOG.debug("filename: {}", fileName);

		String newBody = body + ", filename: " + fileName;
		message.setBody(newBody);
	}
}
