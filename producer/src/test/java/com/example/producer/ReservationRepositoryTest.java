package com.example.producer;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.StringUtils;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.function.Predicate;

import static org.junit.Assert.*;

@DataMongoTest
@RunWith(SpringRunner.class)
public class ReservationRepositoryTest {

	@Autowired
	private ReservationRepository reservationRepository;

	@Test
	public void persistWithRepository () throws Exception {

		Reservation r = new Reservation(null, "Jane");
		Mono<Reservation> save = this.reservationRepository.save(r);
		StepVerifier
			.create(save)
			.expectNextMatches(
				reservation -> StringUtils.hasText(reservation.getId()) &&
					reservation.getName().equalsIgnoreCase("Jane")
			)
			.verifyComplete();
	}

}