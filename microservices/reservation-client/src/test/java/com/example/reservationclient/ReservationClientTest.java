package com.example.reservationclient;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJson;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collection;

/**
 * @author <a href="mailto:josh@joshlong.com">Josh Long</a>
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureJson
@AutoConfigureStubRunner(ids = "com.example:reservation-service:+:8080", workOffline = true)
public class ReservationClientTest {

	@Autowired
	private ReservationClient client;
/*
	@Autowired
	private ObjectMapper objectMapper;*/

	@Test
	public void getReservations() throws Exception {
/*
		String json = this.objectMapper
				.writeValueAsString(Arrays.asList(new Reservation(1L, "Jane"),
						new Reservation(2L, "Josh")));

		WireMock.stubFor(WireMock.get("/reservations")
				.willReturn(WireMock.aResponse()
						.withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE)
						.withStatus(200)
						.withBody(json)
				));*/

		Collection<Reservation> res = this.client.getReservations();
		Assertions.assertThat(res.size()).isEqualTo(2);
		Assertions.assertThat(res.stream().filter(r -> r.getReservationName().equalsIgnoreCase("Jane")).count()).isEqualTo(1);
	}
}
