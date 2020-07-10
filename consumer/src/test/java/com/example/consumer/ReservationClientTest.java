package com.example.consumer;

import com.github.tomakehurst.wiremock.client.WireMock;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

//@AutoConfigureWireMock(port = 8080)
@AutoConfigureStubRunner(
    ids = "com.example:producer:+:8080",
    stubsMode = StubRunnerProperties.StubsMode.LOCAL
)
@RunWith(SpringRunner.class)
@SpringBootTest
public class ReservationClientTest {

    @Autowired
    private ReservationClient client;

    @Test
    public void getAll() {

//        WireMock
//                .stubFor(WireMock
//                        .get("/reservations")
//                        .willReturn(
//                                WireMock
//                                        .aResponse()
//                                        .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
//                                        .withBody("[ { \"id\":\"1\", \"reservationName\":\"Jane\" } ]")
//                        ));


        Flux<Reservation> allReservations = this.client.getAllReservations();
        StepVerifier
                .create(allReservations)
                .expectNextMatches(r -> r.getName().equalsIgnoreCase("Jane") && r.getId() != null)
                .verifyComplete();
    }
}
