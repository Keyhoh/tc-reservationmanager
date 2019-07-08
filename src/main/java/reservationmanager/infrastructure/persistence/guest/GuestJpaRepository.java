package reservationmanager.infrastructure.persistence.guest;

import reservationmanager.infrastructure.persistence.HibernateRepository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class GuestJpaRepository extends HibernateRepository {

    public List<GuestEntity> findAll() {
        CriteriaBuilder cb = openEntityManager().getCriteriaBuilder();
        CriteriaQuery<GuestEntity> cq = cb.createQuery(GuestEntity.class);

        cq.from(GuestEntity.class);

        return openEntityManager().createQuery(cq).getResultList();
    }
}
