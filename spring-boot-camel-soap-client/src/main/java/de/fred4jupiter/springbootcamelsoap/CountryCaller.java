package de.fred4jupiter.springbootcamelsoap;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CountryCaller implements Processor {

	@Autowired
	private CountriesClient countriesClient;

	@Override
	public void process(Exchange exchange) throws Exception {
		String countryName = exchange.getIn().getBody(String.class);
		GetCountryResponse response = countriesClient.getCountry(countryName);
		Country country = response.getCountry();
		exchange.getIn().setBody(country.getCapital(), String.class);
	}

}
