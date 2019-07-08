package reservationmanager.infrastructure.persistence;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public abstract class HibernateRepository {
    private EntityManagerFactory entityManagerFactory;

    protected EntityManager openEntityManager() {
        if (entityManagerFactory == null) {
            entityManagerFactory = Persistence.createEntityManagerFactory("ReservationManager");
        }
        return entityManagerFactory.createEntityManager();
    }
}