package com.example.reservationservice;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

/**
	* @author <a href="mailto:josh@joshlong.com">Josh Long</a>
	*/
public interface ReservationRepository extends ReactiveCrudRepository<Reservation, String> {

	Flux<Reservation> findByName(String name);
}
