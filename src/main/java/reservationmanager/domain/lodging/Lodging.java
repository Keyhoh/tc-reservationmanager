package reservationmanager.domain.lodging;

import reservationmanager.domain.room.Room;

import java.time.LocalDate;

public class Lodging {
    private int numberOfNights;
    private Room room;
    private LocalDate startDate;

    public int getPrice(){
        return numberOfNights * room.getPrice();
    }
}
