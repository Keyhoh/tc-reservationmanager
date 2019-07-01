package application.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import reservationmanager.application.service.ReservationBuilder;
import reservationmanager.domain.guest.Guest;
import reservationmanager.domain.lodging.Lodging;
import reservationmanager.domain.reservation.Reservation;
import reservationmanager.domain.room.Room;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ReservationBuilderTests {

    private Guest unnamedGuest = new Guest("", "", "");

    private Room defaultRoom = new Room();

    private LocalDate defaultLodgingStartOn = LocalDate.now().plusDays(1);
    private int defaultLodgingNumberOfGuests = 2;
    private int defaultLodgingNumberOfNights = 1;
    private Lodging defaultLodging = new Lodging(defaultLodgingStartOn, defaultLodgingNumberOfGuests, defaultLodgingNumberOfNights);

    @Test
    void buildDefaultReservation() {

        assertEquals(ReservationBuilder.initialize().buildWithUnnamedGuest()
                , new Reservation(unnamedGuest, defaultRoom, defaultLodging)
        );

        var name = "guest name";
        var address = "guest name";
        var tel = "guest tel";

        assertEquals(ReservationBuilder.initialize()
                        .guest(name, address, tel)
                        .lodging(defaultLodgingStartOn, defaultLodgingNumberOfGuests, defaultLodgingNumberOfNights)
                        .build()
                , new Reservation(new Guest(name, address, tel), defaultRoom, defaultLodging)
        );

        assertEquals(ReservationBuilder.initialize()
                        .guest(name, tel)
                        .lodging(defaultLodgingStartOn, defaultLodgingNumberOfGuests, defaultLodgingNumberOfNights)
                        .build()
                , new Reservation(new Guest(name, "", tel), defaultRoom, defaultLodging)
        );
    }

    @Test
    void buildCorrectReservationFromAGuest() {

        var name = "guest name";
        var address = "guest address";
        var tel = "guest tel";

        assertEquals(ReservationBuilder.initialize()
                        .guest(name, address, tel)
                        .lodging(defaultLodgingStartOn, defaultLodgingNumberOfGuests, defaultLodgingNumberOfNights)
                        .build()
                , new Reservation(new Guest(name, address, tel), defaultRoom, defaultLodging));

        assertEquals(ReservationBuilder.initialize()
                        .guest(name, tel)
                        .lodging(defaultLodgingStartOn, defaultLodgingNumberOfGuests, defaultLodgingNumberOfNights)
                        .build()
                , new Reservation(new Guest(name, "", tel), defaultRoom, defaultLodging));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/application/service/ReservationBuilderTests/buildCorrectReservationFromALodging.csv", numLinesToSkip = 1)
    void buildCorrectReservationFromALodging(int plusDay, int numberOfGuests, int numberOfNights) {

        var startOn = LocalDate.now().plusDays(plusDay);

        assertEquals(ReservationBuilder.initialize()
                        .lodging(startOn, numberOfGuests, numberOfNights)
                        .buildWithUnnamedGuest()
                , new Reservation(unnamedGuest, defaultRoom, new Lodging(startOn, numberOfGuests, numberOfNights))
        );
    }

    @Test
    void throwsIllegalExceptionByIllegalGuest() {

        var name = "guest name";
        var address = "guest address";
        var tel = "guest tel";

        assertThrows(IllegalArgumentException.class, () -> ReservationBuilder.initialize().guest("", "", "").build());
        assertThrows(IllegalArgumentException.class, () -> ReservationBuilder.initialize().guest(name, "", "").build());
        assertThrows(IllegalArgumentException.class, () -> ReservationBuilder.initialize().guest("", "", tel).build());
        assertThrows(IllegalArgumentException.class, () -> ReservationBuilder.initialize().guest("", "").build());
        assertThrows(IllegalArgumentException.class, () -> ReservationBuilder.initialize().guest("", tel).build());
        assertThrows(IllegalArgumentException.class, () -> ReservationBuilder.initialize().guest("", address, "").build());
    }
}
