package com.example.producer;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.r2dbc.DataR2dbcTest;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

@DataR2dbcTest
public class ReservationRepositoryTest {

    @Autowired
    private ReservationRepository reservationRepository;

    @Test
    public void findAll() throws Exception {

        Flux<Reservation> jane =
                this.reservationRepository
                        .deleteAll()
                        .thenMany(this.reservationRepository
                                .save(new Reservation(null, "Jane")))
                        .thenMany(this.reservationRepository.findAll());
        StepVerifier
                .create(jane)
                .expectNextMatches(reservation -> reservation.getName().equalsIgnoreCase("Jane") && reservation.getId() != null)
                .verifyComplete();
    }
}
