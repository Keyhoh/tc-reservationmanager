package reservationmanager.domain.room;

import reservationmanager.domain.lodging.Lodging;

public class Room {

    public int getPrice(){
        return 2000;
    }

    public boolean accommodateGuestsOf(Lodging lodging){
        int MAX_NUMBER_OF_GUESTS = 2;
        return lodging.getNumberOfGuests() <= MAX_NUMBER_OF_GUESTS;
    }
}
