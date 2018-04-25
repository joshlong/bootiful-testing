package com.example.reservationclient;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
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
		private final ParameterizedTypeReference<Collection<Reservation>> ptr =
			new ParameterizedTypeReference<Collection<Reservation>>() {
			};

		public ReservationClient(RestTemplate restTemplate) {
				this.restTemplate = restTemplate;
		}

		public Collection<Reservation> getAllReservations() {
				return this.restTemplate.exchange("http://localhost:8080/reservations", HttpMethod.GET, null, ptr)
					.getBody();
		}

}


@Data
@AllArgsConstructor
@NoArgsConstructor
class Reservation {

		private Long id;

		private String reservationName;
}
