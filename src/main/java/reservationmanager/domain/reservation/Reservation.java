package reservationmanager.domain.reservation;

import org.jetbrains.annotations.NotNull;
import reservationmanager.domain.guest.Guest;
import reservationmanager.domain.lodging.Lodging;

public class Reservation {
    private Guest guest;
    private Lodging lodging;

    public Reservation(@NotNull Guest guest, @NotNull Lodging lodging) {
        this.guest = guest;
        this.lodging = lodging;
    }

    public int getPrice() {
        return lodging.getPrice();
    }

    public boolean isRegistrable() {
        return guest.isContactable();
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
        result = 31 * result + lodging.hashCode();
        return result;
    }
}
