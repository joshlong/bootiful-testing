package com.example.reservationservice;

import org.assertj.core.api.Assertions;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

/**
	* @author <a href="mailto:josh@joshlong.com">Josh Long</a>
	*/
public class ReservationTest {

		@Test
		public void create() {
				Reservation r = new Reservation(1L, "Jane");
				Assert.assertEquals(r.getReservationName(), "Jane");
				Assert.assertThat(r.getReservationName(), Matchers.equalToIgnoringCase("Jane"));
				Assertions.assertThat(r.getReservationName()).isEqualToIgnoringCase("Jane");
		}
}
