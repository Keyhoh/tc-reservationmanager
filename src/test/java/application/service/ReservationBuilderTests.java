package application.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import reservationmanager.application.service.ReservationBuilder;
import reservationmanager.domain.guest.Guest;
import reservationmanager.domain.lodging.Lodging;
import reservationmanager.domain.reservation.Reservation;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ReservationBuilderTests {

    private Guest unnamedGuest = new Guest("", "", "");

    private String guestName = "guest name";
    private String guestAddress = "guest address";
    private String guestTel = "guest tel";

    private Guest contactableGuestWithAddress = new Guest(guestName, guestAddress, guestTel);
    private Guest contactableGuestWithoutAddress = new Guest(guestName, "", guestTel);

    private LocalDate defaultLodgingStartOn = LocalDate.now().plusDays(1);
    private int defaultLodgingNumberOfGuests = 2;
    private int defaultLodgingNumberOfNights = 1;

    private Lodging defaultLodging = new Lodging(defaultLodgingStartOn, defaultLodgingNumberOfGuests, defaultLodgingNumberOfNights);

    @Test
    void buildCorrectReservation() {

        assertEquals(ReservationBuilder.initialize().build()
                , new Reservation(unnamedGuest, defaultLodging)
        );

        assertEquals(ReservationBuilder.initialize()
                        .guest(guestName, guestAddress, guestTel)
                        .lodging(defaultLodgingStartOn, defaultLodgingNumberOfGuests, defaultLodgingNumberOfNights)
                        .buildWithContactableGuest()
                , new Reservation(contactableGuestWithAddress, defaultLodging)
        );

        assertEquals(ReservationBuilder.initialize()
                        .guest(guestName, guestTel)
                        .lodging(defaultLodgingStartOn, defaultLodgingNumberOfGuests, defaultLodgingNumberOfNights)
                        .buildWithContactableGuest()
                , new Reservation(contactableGuestWithoutAddress, defaultLodging)
        );
    }

    @Test
    void buildCorrectReservationFromAGuest() {

        assertEquals(ReservationBuilder.initialize()
                        .guest(guestName, guestAddress, guestTel)
                        .buildWithContactableGuest()
                , new Reservation(contactableGuestWithAddress, defaultLodging));

        assertEquals(ReservationBuilder.initialize()
                        .guest(guestName, "", guestTel)
                        .buildWithContactableGuest()
                , new Reservation(contactableGuestWithoutAddress, defaultLodging));

        assertEquals(ReservationBuilder.initialize()
                        .guest(guestName, guestTel)
                        .buildWithContactableGuest()
                , new Reservation(contactableGuestWithoutAddress, defaultLodging));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/application/service/ReservationBuilderTests/buildCorrectReservationFromALodging.csv", numLinesToSkip = 1)
    void buildCorrectReservationFromALodging(int plusDay, int numberOfGuests, int numberOfNights) {

        var startOn = LocalDate.now().plusDays(plusDay);

        assertEquals(ReservationBuilder.initialize()
                        .lodging(startOn, numberOfGuests, numberOfNights)
                        .build()
                , new Reservation(unnamedGuest, new Lodging(startOn, numberOfGuests, numberOfNights))
        );

        assertEquals(ReservationBuilder.initialize()
                        .guest(guestName, guestAddress, guestTel)
                        .lodging(startOn, numberOfGuests, numberOfNights)
                        .buildWithContactableGuest()
                , new Reservation(contactableGuestWithAddress, new Lodging(startOn, numberOfGuests, numberOfNights))
        );

        assertEquals(ReservationBuilder.initialize()
                        .guest(guestName, guestTel)
                        .lodging(startOn, numberOfGuests, numberOfNights)
                        .buildWithContactableGuest()
                , new Reservation(contactableGuestWithoutAddress, new Lodging(startOn, numberOfGuests, numberOfNights))
        );
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/application/service/ReservationBuilderTests/throwsIllegalExceptionByIllegalGuestWithAddress.csv", numLinesToSkip = 1)
    void throwsIllegalExceptionByIllegalGuestWithAddress(String name, String address, String tel) {
        Executable executable = () -> ReservationBuilder.initialize().guest(name, address, tel).buildWithContactableGuest();
        if (name == null || address == null || tel == null) {
            assertThrows(IllegalArgumentException.class, executable);
        } else {
            assertThrows(IllegalStateException.class, executable);
        }
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/application/service/ReservationBuilderTests/throwsIllegalExceptionByIllegalGuestWithoutAddress.csv", numLinesToSkip = 1)
    void throwsIllegalExceptionByIllegalGuestWithoutAddress(String name, String tel) {
        Executable executable = () -> ReservationBuilder.initialize().guest(name, tel).buildWithContactableGuest();
        if (name == null || tel == null) {
            assertThrows(IllegalArgumentException.class, executable);
        } else {
            assertThrows(IllegalStateException.class, executable);
        }
    }

    @Test
    void throwsIllegalExceptionByIllegalLodging() {
        var yesterday = LocalDate.now().minusDays(1);
        assertThrows(IllegalArgumentException.class, () -> ReservationBuilder.initialize().lodging(yesterday, defaultLodgingNumberOfGuests, defaultLodgingNumberOfNights).build());
        assertThrows(IllegalArgumentException.class, () -> ReservationBuilder.initialize().lodging(defaultLodgingStartOn, 0, defaultLodgingNumberOfNights).build());
        // capacity of room is two of fixed value by design
        assertThrows(IllegalArgumentException.class, () -> ReservationBuilder.initialize().lodging(defaultLodgingStartOn, 3, defaultLodgingNumberOfNights).build());
        assertThrows(IllegalArgumentException.class, () -> ReservationBuilder.initialize().lodging(defaultLodgingStartOn, defaultLodgingNumberOfGuests, 0).build());
        // max number of nights is four of fixed value by design
        assertThrows(IllegalArgumentException.class, () -> ReservationBuilder.initialize().lodging(defaultLodgingStartOn, defaultLodgingNumberOfGuests, 5).build());
    }
}
