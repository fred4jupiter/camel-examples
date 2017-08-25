package hello;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.ClassUtils;
import org.springframework.ws.client.core.WebServiceTemplate;

import io.spring.guides.gs_producing_web_service.Country;
import io.spring.guides.gs_producing_web_service.Currency;
import io.spring.guides.gs_producing_web_service.GetCountryRequest;
import io.spring.guides.gs_producing_web_service.GetCountryResponse;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class CountryEndpointTest {

	private static final Logger LOG = LoggerFactory.getLogger(CountryEndpointTest.class);

	private Jaxb2Marshaller marshaller = new Jaxb2Marshaller();

	@LocalServerPort
	private int port = 0;

	@Before
	public void init() throws Exception {
		marshaller.setPackagesToScan(ClassUtils.getPackageName(GetCountryRequest.class));
		marshaller.afterPropertiesSet();
	}

	@Test
	public void testSendAndReceive() {
		WebServiceTemplate ws = new WebServiceTemplate(marshaller);
		GetCountryRequest request = new GetCountryRequest();

		final String requestedName = "Spain";
		request.setName(requestedName);

		GetCountryResponse response = (GetCountryResponse) ws.marshalSendAndReceive("http://localhost:" + port + "/ws",
				request);
		LOG.info("response: {}", response);
		assertThat(response).isNotNull();
		Country country = response.getCountry();
		assertNotNull(country);
		assertEquals(requestedName, country.getName());
		assertEquals("Madrid", country.getCapital());
		assertEquals(Currency.EUR, country.getCurrency());
		assertEquals(46704314, country.getPopulation());
	}
}