package reservationmanager.application.service;

import reservationmanager.domain.guest.Guest;
import reservationmanager.domain.lodging.Lodging;
import reservationmanager.domain.reservation.Reservation;
import reservationmanager.domain.room.Room;

import java.time.LocalDate;

public class ReservationBuilder {
    private Guest guest = new Guest("", "", "");
    private Room room = new Room();
    private Lodging lodging = new Lodging(LocalDate.now().plusDays(1), 2, 1);

    public Reservation build() {
        return new Reservation(guest, room, lodging);
    }

    public ReservationBuilder guest(Guest guest){
        this.guest = guest;
        return this;
    }

    public ReservationBuilder lodging(Lodging lodging){
        this.lodging = lodging;
        return this;
    }
}
