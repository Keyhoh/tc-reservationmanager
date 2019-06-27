package reservationmanager.domain.lodging;

import java.text.MessageFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Lodging {
    private LocalDate startDate;
    private int numberOfGuests;
    private int numberOfNights;

    public Lodging(LocalDate startDate, int numberOfGuests, int numberOfNights) {
        validateLodging(startDate, numberOfNights);
        this.startDate = startDate;
        this.numberOfGuests = numberOfGuests;
        this.numberOfNights = numberOfNights;
    }

    private void validateLodging(LocalDate startDate, int numberOfNights) {
        List<String> messageList = new ArrayList<>();
        if (isValidNumberOfNights(numberOfNights)) {
            messageList.add(MessageFormat.format("{0} is illegal number of nights.", numberOfNights));
        }
        if (isValidStartDate(startDate)) {
            messageList.add(MessageFormat.format("{0} is a past date.", startDate));
        }
        if (!messageList.isEmpty()) {
            throw new IllegalArgumentException(String.join(System.lineSeparator(), messageList));
        }
    }

    private boolean isValidNumberOfNights(int numberOfNights) {
        var MAX_NUMBER_OF_NIGHTS = 4;
        return numberOfNights <= 0 || numberOfNights > MAX_NUMBER_OF_NIGHTS;
    }

    private boolean isValidStartDate(LocalDate startDate) {
        return LocalDate.now().isAfter(startDate);
    }

    public int getNumberOfGuests() {
        return numberOfGuests;
    }

    public int getNumberOfNights() {
        return numberOfNights;
    }
}
