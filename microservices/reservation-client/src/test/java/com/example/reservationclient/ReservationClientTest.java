package com.example.reservationclient;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collection;

/**
 * @author <a href="mailto:josh@joshlong.com">Josh Long</a>
 */
@SpringBootTest(classes = ReservationClientApplication.class)
@RunWith(SpringRunner.class)
//@AutoConfigureWireMock (port = 8081)
@AutoConfigureJsonTesters
@AutoConfigureStubRunner(ids = "com.example:reservation-service:+:8081", workOffline = true)
public class ReservationClientTest {

    @Autowired
    private ReservationClient client;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void shouldReturnAllReservations() throws Exception {

        /*
        String json = this.objectMapper.writeValueAsString(
                Arrays.asList(new Reservation(1L, "Jane"), new Reservation(2L, "Bob")));

        WireMock.stubFor(
                WireMock.get(WireMock.urlMatching("/reservations"))
                        .willReturn(WireMock.aResponse()
                                .withStatus(HttpStatus.OK.value())
                                .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE)
                                .withBody(json)));
        */

        Collection<Reservation> reservations = this.client.getReservations();
        Assertions.assertThat(reservations.size()).isEqualTo(2);

        Reservation next = reservations.iterator().next();
        Assertions.assertThat(next.getReservationName()).isEqualTo("Jane");
        Assertions.assertThat(next.getId()).isEqualTo(1L);
    }
}