package com.example.reservationservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

/**
 * @author <a href="mailto:josh@joshlong.com">Josh Long</a>
 */
@RestController
public class ReservationRestController {

	private final ReservationRepository reservationRepository;

	public ReservationRestController(ReservationRepository reservationRepository) {
		this.reservationRepository = reservationRepository;
	}

	@GetMapping("/reservations")
	Collection<Reservation> reservations() {
		return this.reservationRepository.findAll();
	}
}
