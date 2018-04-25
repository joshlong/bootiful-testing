package com.example.reservationclient;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collection;

@RunWith(SpringRunner.class)
//@AutoConfigureWireMock(port = 8080)
@SpringBootTest
@AutoConfigureStubRunner(ids = "com.example:reservation-service:+:8080",
	stubsMode = StubRunnerProperties.StubsMode.LOCAL)
public class ReservationClientApplicationTests {

		@Autowired
		private ReservationClient reservationClient;


//		@Autowired
//		private ObjectMapper objectMapper;

		@Test
		public void getAllReservations() {

/*
				String json = this.objectMapper.writeValueAsString(
					Arrays.asList(new Reservation(1L, "Jane"), new Reservation(2L, "John")));

				WireMock.stubFor(
					WireMock.get("/reservations")
						.willReturn(WireMock.aResponse()
							.withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE)
							.withStatus(200)
							.withBody(json)
						));
*/

				Collection<Reservation> reservations = this.reservationClient.getAllReservations();
				Assertions.assertThat(reservations).isNotNull();
				Assertions.assertThat(reservations.isEmpty()).isFalse();
		}

}
