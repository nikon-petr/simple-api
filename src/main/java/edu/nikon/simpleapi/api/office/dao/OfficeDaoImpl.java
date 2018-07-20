package edu.nikon.simpleapi.api.office.dao;

import edu.nikon.simpleapi.api.office.domain.Office;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * {@inheritDoc}
 */
@Repository
public class OfficeDaoImpl implements OfficeDao {

    private final EntityManager em;

    @Autowired
    public OfficeDaoImpl(EntityManager em) {
        this.em = em;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Office> findAll() {
        TypedQuery<Office> query = em.createQuery("select o from Office o", Office.class);
        return query.getResultList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Office> findById(Long id) {
        Objects.requireNonNull(id);
        Office office = em.find(Office.class, id);
        return Optional.ofNullable(office);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean exists(Long id) {
        Objects.requireNonNull(id);
        String queryString = "select case when (count(o) > 0) " +
                "then true " +
                "else false end " +
                "from Office o where o.id = :id";
        TypedQuery<Boolean> query = em.createQuery(queryString, Boolean.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void save(Office entity) {
        Objects.requireNonNull(entity);
        em.persist(entity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Office> filter(Long orgId, String name, String phone, Boolean active) {
        Objects.requireNonNull(orgId);
        TypedQuery<Office> query = em.createQuery(buildFilterQuery(orgId, name, phone, active));
        return query.getResultList();
    }

    private CriteriaQuery<Office> buildFilterQuery(Long orgId, String name, String phone, Boolean active) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Office> query = em.getCriteriaBuilder().createQuery(Office.class);
        Root<Office> office = query.from(Office.class);

        Predicate orgIdFilter = builder.equal(office.get("organization"), orgId);
        Predicate nameFilter = name == null ?
                builder.conjunction() :
                builder.like(builder.lower(office.get("name")), "%" + name.toLowerCase() + "%");
        Predicate phoneFilter = phone == null ?
                builder.conjunction() :
                builder.equal(office.get("phone"), phone);
        Predicate activeFilter = active == null ?
                builder.conjunction() :
                builder.equal(office.get("active"), active);

        Predicate filter = builder.and(orgIdFilter, nameFilter, phoneFilter, activeFilter);

        return query.where(filter);
    }
}
