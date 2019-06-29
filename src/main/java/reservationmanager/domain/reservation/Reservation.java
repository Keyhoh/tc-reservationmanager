package reservationmanager.domain.reservation;

import org.jetbrains.annotations.NotNull;
import reservationmanager.domain.guest.Guest;
import reservationmanager.domain.lodging.Lodging;
import reservationmanager.domain.room.Room;

public class Reservation {
    private Guest guest;
    private Room room;
    private Lodging lodging;

    public Reservation(@NotNull Guest guest,@NotNull Room room,@NotNull Lodging lodging) {
        validateInitializeArguments(guest, room, lodging);
        this.guest = guest;
        this.room = room;
        this.lodging = lodging;
    }

    public int getPrice() {
        return lodging.getNumberOfNights() * room.getPrice();
    }

    public boolean isRegistrable(){
        return guest.isContactable();
    }

    private void validateInitializeArguments(@NotNull Guest guest,@NotNull Room room,@NotNull Lodging lodging) {
        if (!room.accommodateGuestsOf(lodging)) {
            throw new IllegalArgumentException("Number of gusts is over");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Reservation that = (Reservation) o;

        if (!guest.equals(that.guest)) return false;
        return lodging.equals(that.lodging);
    }

    @Override
    public int hashCode() {
        int result = guest.hashCode();
        result = 31 * result + room.hashCode();
        result = 31 * result + lodging.hashCode();
        return result;
    }
}
