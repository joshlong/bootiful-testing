package com.example.producer;

import io.restassured.RestAssured;
import io.restassured.module.webtestclient.RestAssuredWebTestClient;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;

@Import(ReservationHttpConfiguration.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BaseClass {

    @LocalServerPort
    private int port;

    @Autowired
    private WebTestClient testClient;

    @MockBean
    private ReservationRepository reservationRepository;

    @BeforeEach
    public void setUp() {
        RestAssured.baseURI = "http://localhost:" + this.port;

        RestAssuredWebTestClient.webTestClient(this.testClient);


        Mockito.when(this.reservationRepository.findAll()).thenReturn(Flux.just(new Reservation("1", "Jane")));
    }
}
