package reservationmanager.application.service;

import reservationmanager.domain.reservation.Reservation;
import reservationmanager.domain.reservation.ReservationRepository;

import java.util.Collection;

public class Persistence {
    private ReservationRepository reservationRepository;

    public int register(Reservation reservation) {
        return reservationRepository.create(reservation);
    }

    public Collection<Reservation> findAll() {
        return reservationRepository.findAll();
    }
}
