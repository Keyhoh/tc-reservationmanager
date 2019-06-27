package reservationmanager.domain.reservation;

import java.util.Collection;

public interface ReservationRepository {
    int create(Reservation reservation);
    Collection<Reservation> findAll();
}
