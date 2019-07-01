package reservationmanager.application.service;

import com.google.common.base.Strings;
import reservationmanager.domain.guest.Guest;
import reservationmanager.domain.lodging.Lodging;
import reservationmanager.domain.reservation.Reservation;
import reservationmanager.domain.room.Room;

import java.time.LocalDate;

public class ReservationBuilder {
    private Guest unnamedGuest = new Guest("", "", "");
    private String guestName = "";
    private String guestAddress = "";
    private String guestTel = "";

    private Room room = new Room();
    private Lodging lodging = new Lodging(LocalDate.now().plusDays(1), 2, 1);

    private ReservationBuilder() {
    }

    public static ReservationBuilder initialize() {
        return new ReservationBuilder();
    }

    public Reservation build() {
        if (isContactable()) {
            return new Reservation(new Guest(guestName, guestAddress, guestTel), room, lodging);
        }
        throw new IllegalArgumentException("Guest is not contactable.");
    }

    public Reservation buildWithUnnamedGuest() {
        return new Reservation(unnamedGuest, room, lodging);
    }

    public ReservationBuilder guest(String name, String address, String tel) {
        guestName = name;
        guestAddress = address;
        guestTel = tel;
        return this;
    }

    public ReservationBuilder guest(String name, String tel) {
        return guest(name, "", tel);
    }

    public ReservationBuilder lodging(LocalDate startOn, int numberOfGuests, int numberOfNights) {
        this.lodging = new Lodging(startOn, numberOfGuests, numberOfNights);
        return this;
    }

    private boolean isContactable() {
        return !Strings.isNullOrEmpty(guestName) && !Strings.isNullOrEmpty(guestTel);
    }
}


