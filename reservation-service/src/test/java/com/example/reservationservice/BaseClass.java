package com.example.reservationservice;

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

/**
	* @author <a href="mailto:josh@joshlong.com">Josh Long</a>
	*/
@RunWith(SpringRunner.class)
@Import(ReservationHttpEndpoint.class)
@SpringBootTest(
	properties = "server.port=0",
	webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BaseClass {

	@MockBean
	private ReservationRepository reservationRepository;

	@LocalServerPort
	int port;


	@Before
	public void before() throws Exception {

		Mockito
			.when(this.reservationRepository.findAll())
			.thenReturn(Flux.just(new Reservation("1", "Jane"), new Reservation("2", "John")));

		RestAssured.baseURI = "http://localhost:" + this.port + "";

	}

}
