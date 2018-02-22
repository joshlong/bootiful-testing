package com.example.reservationservice;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author <a href="mailto:josh@joshlong.com">Josh Long</a>
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class ReservationEntityTest {

	@Autowired
	private TestEntityManager testEntityManager;

	@Test
	public void persist() throws Exception {
		Reservation r =
				this.testEntityManager.persistFlushFind(new Reservation(null, "Jane"));
		Assertions.assertThat(r.getReservationName()).isEqualTo("Jane");
		Assertions.assertThat(r.getId()).isNotNull();
	}
}
