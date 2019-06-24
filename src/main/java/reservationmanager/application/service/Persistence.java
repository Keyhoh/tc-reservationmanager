package reservationmanager.application.service;

import reservationmanager.domain.reservation.Reservation;
import reservationmanager.domain.reservation.ReservationRepository;

public class Persistence {
    private ReservationRepository reservationRepository;

    public int create(Reservation reservation){
        return reservationRepository.create(reservation);
    }
}
