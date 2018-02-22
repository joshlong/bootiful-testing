package com.example.reservationclient;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;

/**
 * @author <a href="mailto:josh@joshlong.com">Josh Long</a>
 */
@Component
public class ReservationClient {

	private final RestTemplate restTemplate;

	public ReservationClient(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	public Collection<Reservation> getReservations() {
		return this
				.restTemplate
				.exchange("http://localhost:8080/reservations", HttpMethod.GET, null,
						new ParameterizedTypeReference<Collection<Reservation>>() { })
				.getBody();
	}
}
