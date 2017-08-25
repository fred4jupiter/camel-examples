package de.fred4jupiter.springbootcamelsoap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import countries.wsdl.Country;
import countries.wsdl.Currency;
import countries.wsdl.GetCountryResponse;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CountriesClientMT {

	@Autowired
	private CountriesClient countriesClient;

	@Test
	public void callQuoteClient() {
		String countryName = "Spain";

		GetCountryResponse response = countriesClient.getCountry(countryName);
		assertNotNull(response);
		Country country = response.getCountry();
		assertNotNull(country);
		assertEquals(countryName, country.getName());
		assertEquals("Madrid", country.getCapital());
		assertEquals(Currency.EUR, country.getCurrency());
		assertEquals(46704314, country.getPopulation());
	}
}
