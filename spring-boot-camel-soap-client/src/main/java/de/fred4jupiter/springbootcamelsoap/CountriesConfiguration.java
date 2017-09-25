
package de.fred4jupiter.springbootcamelsoap;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
public class CountriesConfiguration {

	@Bean
	public Jaxb2Marshaller marshaller() {
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		marshaller.setPackagesToScan("de.fred4jupiter.springbootcamelsoap");
		return marshaller;
	}

	@Bean
	public CountriesClient countriesClient(Jaxb2Marshaller marshaller) {
		CountriesClient client = new CountriesClient();
		client.setDefaultUri("http://localhost:8180/ws/countries");
		client.setMarshaller(marshaller);
		client.setUnmarshaller(marshaller);
		return client;
	}

}
