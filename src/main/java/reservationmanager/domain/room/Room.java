package reservationmanager.domain.room;

public class Room {

    public int getPrice() {
        return 2000;
    }

    public boolean accommodateGuestsOf(int numberOfGuests) {
        int MAX_NUMBER_OF_GUESTS = 2;
        return numberOfGuests <= MAX_NUMBER_OF_GUESTS;
    }
}
