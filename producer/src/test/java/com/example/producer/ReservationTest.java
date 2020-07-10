package com.example.producer;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class ReservationTest {

    @Test
    public void persist() {
        Reservation reservation = new Reservation("1", "Jane");
        Assertions.assertEquals(reservation.getName(), "Jane");
        Assertions.assertEquals(reservation.getId(), "1");

    }
}
