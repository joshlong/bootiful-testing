package com.example.reservationservice;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collection;

/**
 * @author <a href="mailto:josh@joshlong.com">Josh Long</a>
 */
@SpringBootTest(classes = ReservationServiceApplication.class)
@RunWith(SpringRunner.class)
@DataJpaTest // Boot 1.4 test slices
public class ReservationRepositoryTest {

    @Autowired
    private TestEntityManager tem;

    @Autowired
    private ReservationRepository reservationRepository;

    @Test
    public void testReservationRepository() throws Exception {
        Reservation bob = tem.persistFlushFind(new Reservation(null, "Bob"));
        Assertions.assertThat(bob).isNotNull();
        Assertions.assertThat(bob.getId()).isNotNull();
        Assertions.assertThat(bob.getReservationName()).isNotNull();
        Assertions.assertThat(bob.getId()).isGreaterThan(0L);
    }

    @Test
    public void testRepositoryCustomQuery() throws Exception {
        tem.persist(new Reservation(null, "Jane"));
        tem.persist(new Reservation(null, "Jane"));
        Collection<Reservation> reservationName = this.reservationRepository.findByReservationName("Jane");
        Assertions.assertThat(reservationName.size()).isEqualTo(2);
    }
}