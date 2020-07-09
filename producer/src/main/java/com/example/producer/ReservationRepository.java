package com.example.producer;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface ReservationRepository
        extends ReactiveCrudRepository<Reservation, String> {
}
