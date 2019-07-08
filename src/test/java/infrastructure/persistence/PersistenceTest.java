package infrastructure.persistence;

import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reservationmanager.infrastructure.persistence.guest.GuestJpaRepository;

import java.io.IOException;
import java.util.Objects;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PersistenceTest {
    private Flyway flyway;
    private GuestJpaRepository guestJpaRepository;

    @BeforeEach
    void initialize() {
        setFlyway();
        flyway.clean();
        flyway.migrate();
        setGuestJpaRepository();
    }

    void setGuestJpaRepository() {
        guestJpaRepository = new GuestJpaRepository();
    }

    void setFlyway() {
        Properties properties = new Properties();
        try {
            var is = this.getClass().getClassLoader().getResourceAsStream("conf/flyway.properties");
            properties.load(Objects.requireNonNull(is));
        } catch (IOException e) {
            e.printStackTrace();
        }
        flyway = Flyway.configure().configuration(properties).load();
    }

    @Test
    void findAll() {
        var guestEntity = guestJpaRepository.findAll();

        assertNotNull(guestEntity);
        assertTrue(guestEntity.isEmpty());
    }
}
