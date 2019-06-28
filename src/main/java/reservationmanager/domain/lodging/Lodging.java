package reservationmanager.domain.lodging;

import java.text.MessageFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Lodging {
    private LocalDate startOn;
    private int numberOfGuests;
    private int numberOfNights;

    public Lodging(LocalDate startOn, int numberOfGuests, int numberOfNights) {
        validateInitializeArguments(startOn, numberOfNights);
        this.startOn = startOn;
        this.numberOfGuests = numberOfGuests;
        this.numberOfNights = numberOfNights;
    }

    private void validateInitializeArguments(LocalDate startOn, int numberOfNights) {
        List<String> messageList = new ArrayList<>();
        if (maxNumberOfNightsIsUnder(numberOfNights)) {
            messageList.add(MessageFormat.format("{0} is or under zero or over max number of nights.", numberOfNights));
        }
        if (todayIsAfter(startOn)) {
            messageList.add(MessageFormat.format("{0} is a past date.", startOn));
        }
        if (!messageList.isEmpty()) {
            throw new IllegalArgumentException(String.join(System.lineSeparator(), messageList));
        }
    }

    private boolean maxNumberOfNightsIsUnder(int numberOfNights) {
        var MAX_NUMBER_OF_NIGHTS = 4;
        return numberOfNights <= 0 || numberOfNights > MAX_NUMBER_OF_NIGHTS;
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
}
