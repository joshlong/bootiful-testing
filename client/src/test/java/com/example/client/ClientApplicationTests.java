package com.example.client;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

@AutoConfigureStubRunner(
        ids = "com.example:service:+:8080",
        stubsMode = StubRunnerProperties.StubsMode.LOCAL
)
//@AutoConfigureWireMock(
//	port = 8080
//)
@SpringBootTest
@RunWith(SpringRunner.class)
public class ClientApplicationTests {

    @Autowired
    private ReservationClient client;

    @Test
    public void get() {

		/*WireMock
			.stubFor(WireMock
			.get(WireMock.urlEqualTo("/reservations"))
			.willReturn(
				WireMock.aResponse()
					.withBody("[  {  \"id\" : \"1\", \"reservationName\" : \"Jane\"   }  ]")
					.withStatus(HttpStatus.OK.value())
					.withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
			));*/
        Flux<Reservation> allReservations = this.client.getAllReservations();
        StepVerifier
                .create(allReservations)
                .expectNextMatches(r -> r.getName().equalsIgnoreCase("Jane"))
                .verifyComplete();
    }
}
