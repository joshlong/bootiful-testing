package com.example.reservationservice;

import org.assertj.core.api.Assertions;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

public class ReservationTest {

	@Test
	public void create() throws Exception {
		Reservation reservation = new Reservation("1", "Jane");
		Assert.assertEquals(reservation.getId(), "1");
		Assert.assertThat(reservation.getName(), Matchers.is("Jane"));
		Assertions.assertThat(reservation.getName()).isEqualToIgnoringCase("jane");
	}
}
