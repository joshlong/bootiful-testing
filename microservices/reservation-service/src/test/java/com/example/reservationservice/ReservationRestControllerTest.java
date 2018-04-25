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

import java.util.Arrays;
import java.util.Collections;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
	* @author <a href="mailto:josh@joshlong.com">Josh Long</a>
	*/
@WebMvcTest
@RunWith(SpringRunner.class)
public class ReservationRestControllerTest {

		@MockBean
		private ReservationRepository reservationRepository;

		@Autowired
		private MockMvc mvc;

		@Test
		public void get() throws Exception {

				Mockito.when(this.reservationRepository.findAll())
					.thenReturn(Collections.singletonList(new Reservation(null, "Jane")));

				this.mvc.perform(MockMvcRequestBuilders.get("/reservations"))
					.andExpect(status().isOk())
					.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andExpect(jsonPath("@.[0].reservationName").value("Jane"));
		}
}
