package reservationmanager.infrastructure.entity.room;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "room")
@Data
public class RoomEntity {
    @Id
    private UUID id;
    private Integer price;
    private Integer capacity;
}
