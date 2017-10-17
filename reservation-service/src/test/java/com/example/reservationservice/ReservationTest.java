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
    public void testPersistence() throws Exception {
        Reservation reservation = new Reservation(null, "Jane");
        Assert.assertEquals(reservation.getReservationName(), "Jane");// junit
        Assert.assertThat(reservation.getReservationName(), Matchers.is("Jane")); // hamcrest
        Assertions.assertThat(reservation.getReservationName()).isEqualTo("Jane"); // assertj
    }
}