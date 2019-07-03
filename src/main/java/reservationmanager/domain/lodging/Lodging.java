package reservationmanager.domain.lodging;

import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;

public class Lodging {
    private LocalDate startOn;
    private int numberOfGuests;
    private int numberOfNights;

    public Lodging(@NotNull LocalDate startOn, int numberOfGuests, int numberOfNights) {
        validateInitializeArguments(startOn, numberOfGuests, numberOfNights);
        this.startOn = startOn;
        this.numberOfGuests = numberOfGuests;
        this.numberOfNights = numberOfNights;
    }

    private void validateInitializeArguments(@NotNull LocalDate startOn, int numberOfGuests, int numberOfNights) {
        if (maxNumberOfNightsIsUnder(numberOfNights) || !isPositive(numberOfGuests) || todayIsAfter(startOn)) {
            throw new IllegalArgumentException("Impossible lodging");
        }
    }

    private boolean maxNumberOfNightsIsUnder(int numberOfNights) {
        var MAX_NUMBER_OF_NIGHTS = 4;
        return numberOfNights <= 0 || numberOfNights > MAX_NUMBER_OF_NIGHTS;
    }

    private boolean isPositive(int numberOfGuests) {
        return numberOfGuests > 0;
    }

    private boolean todayIsAfter(LocalDate startDate) {
        return LocalDate.now().isAfter(startDate);
    }

    public int getNumberOfGuests() {
        return numberOfGuests;
    }

    public int getNumberOfNights() {
        return numberOfNights;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Lodging lodging = (Lodging) o;

        if (numberOfGuests != lodging.numberOfGuests) return false;
        if (numberOfNights != lodging.numberOfNights) return false;
        return startOn.equals(lodging.startOn);
    }

    @Override
    public int hashCode() {
        int result = startOn.hashCode();
        result = 31 * result + numberOfGuests;
        result = 31 * result + numberOfNights;
        return result;
    }
}
