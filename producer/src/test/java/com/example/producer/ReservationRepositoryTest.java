package com.example.producer;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.r2dbc.DataR2dbcTest;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

@DataR2dbcTest
@RunWith(SpringRunner.class)
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
