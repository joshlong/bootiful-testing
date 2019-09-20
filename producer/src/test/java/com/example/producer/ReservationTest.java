package com.example.producer;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;


public class ReservationTest {

	@Test
	public void create() throws Exception {
		Reservation r = new Reservation("1", "Jane");
		Assert.assertThat(r.getId(), Matchers.equalTo("1"));
		Assert.assertThat(r.getName(), Matchers.equalTo("Jane"));
	}
}