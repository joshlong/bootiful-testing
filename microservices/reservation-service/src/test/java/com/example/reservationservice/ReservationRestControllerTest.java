package com.example.reservationservice;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Collections;

/**
 * @author <a href="mailto:josh@joshlong.com">Josh Long</a>
 */
@WebMvcTest
@RunWith(SpringRunner.class)
public class ReservationRestControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ReservationRepository reservationRepository;

	@Test
	public void getAllReservations() throws Exception {

		Mockito.when(this.reservationRepository.findAll())
				.thenReturn(Collections.singletonList(new Reservation(1L, "Jane")));

		this.mockMvc
				.perform(MockMvcRequestBuilders.get("/reservations"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(MockMvcResultMatchers.jsonPath("@.[0].reservationName").value("Jane"));

	}
}
