package reservationmanager.domain.reservation;

import reservationmanager.domain.guest.Guest;
import reservationmanager.domain.lodging.Lodging;

public class Reservation {
    private Guest guest;
    private Lodging lodging;

    public int getPrice() {
        return lodging.getPrice();
    }
}
