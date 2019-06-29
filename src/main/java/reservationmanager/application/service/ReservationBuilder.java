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

    private ReservationBuilder(){}

    public static ReservationBuilder initialize(){
        return new ReservationBuilder();
    }

    public Reservation build() {
        return new Reservation(guest, room, lodging);
    }

    public ReservationBuilder guest(String name, String address, String tel) {
        this.guest = new Guest(name, address, tel);
        return this;
    }

    public ReservationBuilder guest(String name, String tel) {
        return guest(name, "", tel);
    }

    public ReservationBuilder lodging(LocalDate startOn, int numberOfGuests, int numberOfNights) {
        this.lodging = new Lodging(startOn, numberOfGuests, numberOfNights);
        return this;
    }
}


