package com.example.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
class ReservationHttpConfiguration {

    @Bean
    RouterFunction<ServerResponse> http(ReservationRepository rr) {
        return route()
                .GET("/reservations", request -> ServerResponse.ok().body(rr.findAll(), Reservation.class))
                .build();
    }
}
