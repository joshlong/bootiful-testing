package com.example.reservationservice;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;


@Import(ReservationHttpEndpoint.class)
@WebFluxTest
@RunWith(SpringRunner.class)
public class ReservationHttpEndpointTest {

	@MockBean
	private ReservationRepository reservationRepository;

	@Autowired
	private WebTestClient client;

	@Test
	public void getAllReservations() throws Exception {

		Mockito
			.when(this.reservationRepository.findAll())
			.thenReturn(Flux.just(new Reservation("1", "Jane")));

		this.client
			.get()
			.uri("http://localhost:8080/reservations")
			.exchange()
			.expectStatus().isOk()
			.expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8)
			.expectBody() //
			.jsonPath("@.[0].name").isEqualTo("Jane")
			.jsonPath("@.[0].id").isEqualTo("1");


	}
}
