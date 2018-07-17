package edu.nikon.simpleapi.api.organization.dao;

import edu.nikon.simpleapi.api.organization.domain.Organization;
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
public class OrganizationDaoImpl implements OrganizationDao {

    private final EntityManager em;

    @Autowired
    public OrganizationDaoImpl(EntityManager em) {
        this.em = em;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Organization> findAll() {
        TypedQuery<Organization> query = em.createQuery("select o from Organization o", Organization.class);
        return query.getResultList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Organization> findById(Long id) {
        Objects.requireNonNull(id);
        Organization organization = em.find(Organization.class, id);
        return Optional.ofNullable(organization);
    }

    @Override
    public boolean exists(Long id) {
        Objects.requireNonNull(id);
        String queryString = "select case when (count(o) > 0) " +
                "then true " +
                "else false end " +
                "from Organization o where o.id = :id";
        TypedQuery<Boolean> query = em.createQuery(queryString, Boolean.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void save(Organization entity) {
        Objects.requireNonNull(entity);
        em.persist(entity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isUnique(String inn, String kpp) {
        Objects.requireNonNull(inn);
        Objects.requireNonNull(kpp);

        String queryString = "select case when (count(o) = 0) " +
                "then true " +
                "else false end " +
                "from Organization o where o.inn = :inn and o.kpp = :kpp";
        TypedQuery<Boolean> query = em.createQuery(queryString, Boolean.class);
        query.setParameter("inn", inn);
        query.setParameter("kpp", kpp);
        return query.getSingleResult();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isUnique(long id, String inn, String kpp) {
        Objects.requireNonNull(inn);
        Objects.requireNonNull(kpp);

        String queryString = "select case when (count(o) = 0) " +
                "then true " +
                "else false end " +
                "from Organization o " +
                "where o.inn = :inn and o.kpp = :kpp and not o.id = :id";
        TypedQuery<Boolean> query = em.createQuery(queryString, Boolean.class);
        query.setParameter("id", id);
        query.setParameter("inn", inn);
        query.setParameter("kpp", kpp);
        return query.getSingleResult();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Organization> filter(String name, String inn, Boolean active) {
        Objects.requireNonNull(name);
        TypedQuery<Organization> query = em.createQuery(buildFilterQuery(name, inn, active));
        return query.getResultList();
    }

    private CriteriaQuery<Organization> buildFilterQuery(String name, String inn, Boolean active) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Organization> query = em.getCriteriaBuilder().createQuery(Organization.class);
        Root<Organization> organization = query.from(Organization.class);

        Predicate nameFilter = builder.like(builder.lower(organization.get("name")), "%" + name.toLowerCase() + "%");
        Predicate innFilter = inn == null ?
                builder.conjunction() :
                builder.equal(organization.get("inn"), inn);
        Predicate activeFilter = active == null ?
                builder.conjunction() :
                builder.equal(organization.get("active"), active);

        Predicate filter = builder.and(nameFilter, innFilter, activeFilter);

        return query.where(filter);
    }
}
