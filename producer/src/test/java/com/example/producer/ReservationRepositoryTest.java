package com.example.producer;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import reactor.test.StepVerifier;

@DataMongoTest
public class ReservationRepositoryTest {

    @Autowired
    private ReservationRepository reservationRepository;

    @Test
    public void fetch() {
        StepVerifier
                .create(this.reservationRepository.count())
                .expectNextMatches(count -> count > 0)
                .verifyComplete();
    }

}
