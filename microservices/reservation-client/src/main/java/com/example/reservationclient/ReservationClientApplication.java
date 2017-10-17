package com.example.reservationclient;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;

@SpringBootApplication
public class ReservationClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReservationClientApplication.class, args);
    }

    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }
}

@Data
@AllArgsConstructor
@NoArgsConstructor
class Reservation {
    private Long id;
    private String reservationName;
}

@Component
class ReservationClient {

    private final RestTemplate restTemplate;

    ReservationClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Collection<Reservation> getReservations() {
        ParameterizedTypeReference<Collection<Reservation>> ptr =
                new ParameterizedTypeReference<Collection<Reservation>>() {
                };
        ResponseEntity<Collection<Reservation>> responseEntity =
                this.restTemplate.exchange("http://localhost:8081/reservations", HttpMethod.GET, null, ptr);
        return responseEntity.getBody();
    }
}