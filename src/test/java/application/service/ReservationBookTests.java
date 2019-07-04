package application.service;

import org.junit.jupiter.api.Test;
import reservationmanager.application.service.ReservationBook;
import reservationmanager.application.service.ReservationBuilder;
import reservationmanager.domain.reservation.ReservationRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ReservationBookTests {
    private ReservationRepository reservationRepository = mock(ReservationRepository.class);
    private ReservationBook reservationBook = new ReservationBook(reservationRepository);

    @Test
    void throwsIllegalExceptionByIllegalReservation() {
        var unnamedGuestReservation = ReservationBuilder.initialize().buildWithUnnamedGuest();
        assertThrows(IllegalStateException.class, () -> reservationBook.written(unnamedGuestReservation));
    }

    @Test
    void writeReservationCorrectly() {
        var reservationWithDefaultLodging = ReservationBuilder.initialize().guest("guest name", "guest tel").build();

        when(reservationRepository.create(reservationWithDefaultLodging)).thenReturn(reservationWithDefaultLodging);

        assertEquals(reservationBook.written(reservationWithDefaultLodging), reservationWithDefaultLodging);
    }
}
