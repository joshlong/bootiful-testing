package com.example.reservationservice;

import org.springframework.data.jpa.repository.JpaRepository;

/**
	* @author <a href="mailto:josh@joshlong.com">Josh Long</a>
	*/
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

}
