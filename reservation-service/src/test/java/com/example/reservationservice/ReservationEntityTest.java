package com.example.reservationservice;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.StringUtils;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@RunWith(SpringRunner.class)
@DataMongoTest
public class ReservationEntityTest {

	@Autowired
	private ReactiveMongoTemplate reactiveMongoTemplate;

	@Test
	public void persist() throws Exception {
		Mono<Reservation> jane = this.reactiveMongoTemplate
			.save(new Reservation(null, "Jane"));
		StepVerifier
			.create(jane)
			.expectNextMatches(r -> StringUtils.hasText(r.getId()))
			.verifyComplete();

	}
}
