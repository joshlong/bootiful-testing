package com.example.producer;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

public class ReservationPojoTest {

	@Test
	public void create() throws Exception {
		Reservation re = new Reservation("1", "Jane");
		Assert.assertNotNull(re.getName());
		Assert.assertThat(re.getId(), Matchers.equalToIgnoringCase("1"));
	}
}
