package com.example.reservationclient;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.util.function.Predicate;

@RunWith(SpringRunner.class)
@SpringBootTest
//@AutoConfigureWireMock(port = 8080)
@AutoConfigureStubRunner(
	ids = "com.example:reservation-service:+:8080",
	stubsMode = StubRunnerProperties.StubsMode.LOCAL)
public class ReservationClientApplicationTests {

	@Autowired
	private ReservationClient client;

	@Test
	public void contextLoads() throws Exception {


	/*
		WireMock.stubFor(WireMock.get(
			WireMock.urlEqualTo("/reservations"))
			.willReturn(WireMock
				.aResponse()
				.withBody("[   {\"id\" : \"1\" , \"name\":\"Jane\"} , {\"id\" : \"2\" , \"name\":\"John\"} ]")
				.withStatus(HttpStatus.OK.value())
				.withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
			));
	*/

		Flux<Reservation> allReservations = this.client.getAllReservations();
		Predicate<Reservation> reservationPredicate = r -> r.getName().equalsIgnoreCase("Jane") || r.getName().equalsIgnoreCase("John");
		StepVerifier
			.create(allReservations)
			.expectNextMatches(reservationPredicate)
			.expectNextMatches(reservationPredicate)
			.verifyComplete();

	}

}
