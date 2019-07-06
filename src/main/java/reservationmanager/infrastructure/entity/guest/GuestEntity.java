package reservationmanager.infrastructure.entity.guest;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "guest")
@Data
public class GuestEntity {
    @Id
    private UUID id;
    private String name;
    private String address;
    private String tel;
}
