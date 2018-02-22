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
	public void create() throws Exception {

		Reservation reservation = new Reservation(1L, "Josh");
		Assert.assertEquals("Josh", reservation.getReservationName());
		Assert.assertThat(1L, Matchers.is(reservation.getId()));
		Assertions.assertThat(reservation.getReservationName()).isNotBlank();
	}
}
