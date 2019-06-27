package reservationmanager.application.service;

import reservationmanager.domain.guest.Guest;
import reservationmanager.domain.lodging.Lodging;
import reservationmanager.domain.reservation.Reservation;
import reservationmanager.domain.room.Room;

public class Factory {
    public static Reservation createReservation(Guest guest, Room room, Lodging lodging) {
        return new Reservation(guest, room, lodging);
    }
}
