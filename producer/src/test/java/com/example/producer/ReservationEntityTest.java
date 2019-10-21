package com.example.producer;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;


@DataMongoTest
@RunWith(SpringRunner.class)
public class ReservationEntityTest {

	@Autowired
	private ReservationRepository reservationRepository;

	@Test
	public void persist() {
		Reservation r = new Reservation(null, "Jane");
		Mono<Reservation> save = this.reservationRepository.save(r);
		StepVerifier
			.create(save)
			.expectNextMatches(re -> re.getId() != null && re.getName().equalsIgnoreCase("Jane"))
			.verifyComplete();
	}
}
