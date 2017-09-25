package de.fred4jupiter.springbootcamelsoap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

public class CountriesClient extends WebServiceGatewaySupport {

	private static final Logger log = LoggerFactory.getLogger(CountriesClient.class);

	public GetCountryResponse getCountry(String countryName) {
		GetCountryRequest request = new GetCountryRequest();
		request.setName(countryName);

		log.info("Requesting country {}", countryName);

		GetCountryResponse response = (GetCountryResponse) getWebServiceTemplate().marshalSendAndReceive(request,
				new SoapActionCallback("getCountry"));
		return response;
	}

}