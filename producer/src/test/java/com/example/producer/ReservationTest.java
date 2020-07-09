package com.example.producer;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ReservationTest {

    @Test
    public void persist() {
        Reservation reservation = new Reservation("1", "Jane");
        Assertions.assertEquals(reservation.getId(), "1");
        Assertions.assertEquals(reservation.getName(), "Jane");
    }

}
