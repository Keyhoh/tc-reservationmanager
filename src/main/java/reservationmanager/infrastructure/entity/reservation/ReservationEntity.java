package reservationmanager.infrastructure.entity.reservation;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "reservation")
@Data
public class ReservationEntity {
    @Id
    private UUID id;
    private UUID guestId;
    private UUID lodgingId;
}
