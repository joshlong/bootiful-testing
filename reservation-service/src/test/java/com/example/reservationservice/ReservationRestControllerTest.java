package com.example.reservationservice;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;

/**
 * @author <a href="mailto:josh@joshlong.com">Josh Long</a>
 */
@SpringBootTest(classes = ReservationServiceApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class ReservationRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean // replaces beans in the `ApplicationContext` of the same type or it contributes a new one
    private ReservationRepository reservationRepository;

    @Test
    public void shouldReturnAllReservations() throws Exception {

        List<Reservation> reservations = Arrays.asList(new Reservation(1L, "Jane"),
                new Reservation(2L, "Bob"));

        // turn the mock into a stub w/ Mockito
        Mockito.when(this.reservationRepository.findAll()).thenReturn(reservations);

        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/reservations"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.jsonPath("@.[0].id").value(1L))
                .andExpect(MockMvcResultMatchers.jsonPath("@.[0].reservationName").value("Jane"));
    }

}