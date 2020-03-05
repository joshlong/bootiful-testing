package com.example.producer;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

public class ReservationPojoTest {

	@Test
	public void persist() {
		Reservation reservation = new Reservation("1", "Josh");
		Assert.assertEquals(reservation.getId(), "1");
		Assert.assertEquals(reservation.getName(), "Josh");
		Assert.assertThat(reservation.getId(), Matchers.notNullValue());
	}
}
