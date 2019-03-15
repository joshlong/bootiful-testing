package com.example.reservationservice;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.util.function.Predicate;

@RunWith(SpringRunner.class)
@DataMongoTest
public class ReservationRepositoryTest {

	@Autowired
	private ReservationRepository repository;

	@Test
	public void findByName () throws Exception {

		Flux<Reservation> reservationFlux = this.repository
			.deleteAll()
			.thenMany(
				Flux
					.just("A", "B", "C")
					.map(name -> new Reservation(null, name))
					.flatMap(r -> this.repository.save(r))
			)
			.thenMany(this.repository.findAll());

		Predicate<Reservation> reservationPredicate = r -> r.getName().equalsIgnoreCase("A") ||
			r.getName().equalsIgnoreCase("B") ||
			r.getName().equalsIgnoreCase("C");
		StepVerifier
			.create(reservationFlux)
			.expectNextMatches(reservationPredicate)
			.expectNextMatches(reservationPredicate)
			.expectNextMatches(reservationPredicate)
			.verifyComplete();

	}

}
