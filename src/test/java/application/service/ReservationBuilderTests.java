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
    void buildCorrectReservation() {

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

        assertThrows(IllegalArgumentException.class, () -> ReservationBuilder.initialize().guest(null, null, null).build());
        assertThrows(IllegalArgumentException.class, () -> ReservationBuilder.initialize().guest(name, null, null).build());
        assertThrows(IllegalArgumentException.class, () -> ReservationBuilder.initialize().guest(null, address, null).build());
        assertThrows(IllegalArgumentException.class, () -> ReservationBuilder.initialize().guest(null, null, tel).build());
        assertThrows(IllegalArgumentException.class, () -> ReservationBuilder.initialize().guest(name, address, null).build());
        assertThrows(IllegalArgumentException.class, () -> ReservationBuilder.initialize().guest(null, address, tel).build());
        assertThrows(IllegalArgumentException.class, () -> ReservationBuilder.initialize().guest(null, null).build());
        assertThrows(IllegalArgumentException.class, () -> ReservationBuilder.initialize().guest(name, null).build());
        assertThrows(IllegalArgumentException.class, () -> ReservationBuilder.initialize().guest(null, tel).build());

        assertThrows(IllegalStateException.class, () -> ReservationBuilder.initialize().guest("", "", "").build());
        assertThrows(IllegalStateException.class, () -> ReservationBuilder.initialize().guest(name, "", "").build());
        assertThrows(IllegalStateException.class, () -> ReservationBuilder.initialize().guest("", address, "").build());
        assertThrows(IllegalStateException.class, () -> ReservationBuilder.initialize().guest("", "", tel).build());
        assertThrows(IllegalStateException.class, () -> ReservationBuilder.initialize().guest(name, address, "").build());
        assertThrows(IllegalStateException.class, () -> ReservationBuilder.initialize().guest("", address, tel).build());
        assertThrows(IllegalStateException.class, () -> ReservationBuilder.initialize().guest("", "").build());
        assertThrows(IllegalStateException.class, () -> ReservationBuilder.initialize().guest(name, "").build());
        assertThrows(IllegalStateException.class, () -> ReservationBuilder.initialize().guest("", tel).build());
    }

    @Test
    void throwsIllegalExceptionByIllegalLodging() {
        var yesterday = LocalDate.now().minusDays(1);
        assertThrows(IllegalArgumentException.class, ()-> ReservationBuilder.initialize().lodging(yesterday, defaultLodgingNumberOfGuests,defaultLodgingNumberOfNights).build());
        assertThrows(IllegalArgumentException.class, () -> ReservationBuilder.initialize().lodging(defaultLodgingStartOn, 0, defaultLodgingNumberOfNights).build());
        assertThrows(IllegalArgumentException.class, () -> ReservationBuilder.initialize().lodging(defaultLodgingStartOn, defaultLodgingNumberOfGuests, 0).build());
        assertThrows(IllegalArgumentException.class, () -> ReservationBuilder.initialize().lodging(defaultLodgingStartOn, defaultLodgingNumberOfGuests, 5).build());
    }

    @Test
    void throwsIllegalExceptionByIllegalReservation(){
        assertThrows(IllegalStateException.class, ()->ReservationBuilder.initialize().lodging(defaultLodgingStartOn, 3,defaultLodgingNumberOfNights).build());
    }
}
