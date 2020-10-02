package com.example.consumer;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@Component
@RequiredArgsConstructor
public class ReservationClient {

    private final WebClient webClient;

    public Flux<Reservation> getAllReservations() {
        return this.webClient
                .get()
                .uri("http://localhost:8081/reservations")
                .retrieve()
                .bodyToFlux(Reservation.class);
    }
}
