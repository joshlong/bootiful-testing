package com.example.service;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;

@WebFluxTest
@AutoConfigureWebTestClient
@Import(ReservationHttpConfiguration.class)
public class ReservationHttpTest {

    @MockBean
    private ReservationRepository reservationRepository;

    @Autowired
    private WebTestClient testClient;

    @Test
    void getAllReservations() {
        Mockito.when(this.reservationRepository.findAll())
                .thenReturn(Flux.just(new Reservation("1", "Jane")));

        this.testClient
                .get()
                .uri("http://localhost:8080/reservations")
                .exchange()
                .expectBody()
                .jsonPath("@.[0].name").isEqualTo("Jane")
        ;
    }
}
