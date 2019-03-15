package com.example.reservationservice;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
class Reservation {
	private String id;
	private String name;
}
