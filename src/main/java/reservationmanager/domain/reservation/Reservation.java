package reservationmanager.domain.reservation;

import reservationmanager.domain.guest.Guest;
import reservationmanager.domain.lodging.Lodging;
import reservationmanager.domain.room.Room;

public class Reservation {
    private Guest guest;
    private Room room;
    private Lodging lodging;

    public Reservation(Guest guest, Room room, Lodging lodging) {

        this.guest = guest;
        this.room = room;
        this.lodging = lodging;
    }

    public int getPrice() {
        return lodging.getNumberOfNights() * room.getPrice();
    }

    private void validateReservation(Guest guest,Room room, Lodging lodging){
        if(!room.allowLodging(lodging)){
            throw new IllegalArgumentException("Number of gusts is over");
        }
    }
}
