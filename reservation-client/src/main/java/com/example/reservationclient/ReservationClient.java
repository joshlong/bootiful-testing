package com.example.reservationclient;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

/**
	* @author <a href="mailto:josh@joshlong.com">Josh Long</a>
	*/
@Component
class ReservationClient {

	private final WebClient webClient;

	ReservationClient(WebClient webClient) {
		this.webClient = webClient;
	}


	Flux<Reservation> getAllReservations() {
		return this.webClient
			.get()
			.uri("http://localhost:8080/reservations")
			.retrieve()
			.bodyToFlux(Reservation.class);
	}
}

@Data
@AllArgsConstructor
class Reservation {
	private String id, name;
}