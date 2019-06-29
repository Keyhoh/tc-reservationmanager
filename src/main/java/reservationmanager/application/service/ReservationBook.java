package reservationmanager.application.service;

import reservationmanager.domain.reservation.Reservation;
import reservationmanager.domain.reservation.ReservationRepository;

import java.util.Collection;

public class ReservationBook {
    private ReservationRepository reservationRepository;

    public Reservation written(Reservation reservation) {
        if(!reservation.isRegistrable()){
            throw new IllegalStateException("Guest is not contactable");
        }
        return reservationRepository.create(reservation);
    }

    public Collection<Reservation> readAll() {
        return reservationRepository.findAll();
    }

    public int overwritten(Reservation reservation) {
        return reservationRepository.update(reservation);
    }
}
