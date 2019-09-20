package com.example.producer;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Flux;

@RunWith(SpringRunner.class)
@SpringBootTest(
	webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
	properties = "server.port=0"
)
@Import(ReservationHttpConfig.class)
public class BaseClass {

	@LocalServerPort
	private int port;

	@MockBean
	private ReservationRepository reservationRepository;

	@Before
	public void before() throws Exception {
		RestAssured.baseURI = "http://localhost:" + this.port + "/";
		Mockito
			.when(this.reservationRepository.findAll())
			.thenReturn(Flux.just(new Reservation("1", "Jane")));
	}
}
