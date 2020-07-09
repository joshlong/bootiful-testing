package com.example.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ReservationTest {
    
    @Test
    void persist() {
        Reservation reservation = new Reservation("1", "Name");
        Assertions.assertEquals(reservation.getId(), "1");
        Assertions.assertEquals(reservation.getName(), "Name");
    }
}
