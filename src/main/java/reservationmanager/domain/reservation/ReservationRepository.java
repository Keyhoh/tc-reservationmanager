package reservationmanager.domain.reservation;

import java.util.Collection;

public interface ReservationRepository {
    Reservation create(Reservation reservation);
    Collection<Reservation> findAll();
    int update(Reservation reservation);
}
