package com.example.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

@DataMongoTest
public class ReservationRepositoryTest {

    @Autowired
    private ReservationRepository reservationRepository;

    @Test
    void findByName() {

        Flux<Reservation> george =
                this.reservationRepository
                        .deleteAll()
                        .thenMany(this.reservationRepository.save(new Reservation(null, "George")))
                        .thenMany(this.reservationRepository.findByName("George"));
        StepVerifier
                .create(george)
                .expectNextMatches(r -> r.getName().equalsIgnoreCase("George"))
                .verifyComplete();
    }
}
