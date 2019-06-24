package reservationmanager.application.service;

import reservationmanager.domain.reservation.Reservation;

public class Factory {
    public static Reservation reservation(){
        return new Reservation();
    }
}
