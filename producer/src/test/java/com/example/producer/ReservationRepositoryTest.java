package com.example.producer;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

@DataMongoTest
@RunWith(SpringRunner.class)
public class ReservationRepositoryTest {

	@Autowired
	private ReservationRepository reservationRepository;

	@Test
	public void findByName() {
		Flux<Reservation> reservationFlux = this.reservationRepository
			.deleteAll()
			.thenMany(Flux.just("A", "B", "B", "C").flatMap(x -> this.reservationRepository.save(new Reservation(null, x))))
			.thenMany(this.reservationRepository.findByName("B"));

		StepVerifier
			.create(reservationFlux)
			.expectNextCount(2)
			.verifyComplete();
	}
}
