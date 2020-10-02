package com.example.consumer;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

//@AutoConfigureWireMock
@AutoConfigureStubRunner(
        ids = "com.example:producer:+:8081",
        stubsMode = StubRunnerProperties.StubsMode.LOCAL
)
@SpringBootTest
public class ReservationClientTest {

    @Autowired
    private ReservationClient client;

    @Test
    public void getAll() {

      /*  WireMock
                .stubFor(WireMock
                        .get("/reservations")
                        .willReturn(
                                WireMock
                                        .aResponse()
                                        .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                                        .withBody("[ { \"id\":\"1\", \"reservationName\":\"Jane\" } ]")
                        ));
*/

        Flux<Reservation> allReservations = this.client.getAllReservations();
        StepVerifier
                .create(allReservations)
                .expectNextMatches(r -> r.getName().equalsIgnoreCase("Jane") && r.getId() != null)
                .verifyComplete();

    }
}
