package de.fred4jupiter.springbootcamelsoap;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import hello.wsdl.GetQuoteResponse;

@RunWith(SpringRunner.class)
@SpringBootTest
public class QuoteClientIT {

	private static final Logger LOG = LoggerFactory.getLogger(QuoteClientIT.class);

	@Autowired
	private QuoteClient quoteClient;

	@Test
	public void callQuoteClient() {
		String ticker = "ORCL";

		GetQuoteResponse response = quoteClient.getQuote(ticker);
		assertNotNull(response);
		String result = response.getGetQuoteResult();
		assertNotNull(result);
		LOG.info("Stock Quotes for {} is {}", ticker, result);
	}
}
