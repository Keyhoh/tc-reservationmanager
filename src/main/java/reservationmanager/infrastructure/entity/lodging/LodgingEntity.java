package reservationmanager.infrastructure.entity.lodging;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "lodging")
@Data
public class LodgingEntity {
    @Id
    private UUID id;
    private UUID roomId;
    private LocalDate startOn;
    private Integer numberOfGuests;
    private Integer numberOfNights;
}
