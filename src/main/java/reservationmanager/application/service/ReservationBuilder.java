package reservationmanager.application.service;

import com.google.common.base.Strings;
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

    public ReservationBuilder guest(String name, String address, String tel) {
        // 業務上宿泊客の名前と連絡先は必須（無断キャンセル等）
        if (Strings.isNullOrEmpty(name) || Strings.isNullOrEmpty(tel)) {
            throw new IllegalArgumentException("name and tel is required.");
        }
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
