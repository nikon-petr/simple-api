package edu.nikon.simpleapi.api.catalog.dao;

import edu.nikon.simpleapi.api.catalog.domain.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * {@inheritDoc}
 */
@Repository
public class CountryDaoImpl implements CountryDao {

    private final EntityManager em;

    @Autowired
    public CountryDaoImpl(EntityManager em) {
        this.em = em;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Country> findAll() {
        TypedQuery<Country> query = em.createQuery("select c from Country c", Country.class);
        return query.getResultList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Country> findById(String code) {
        Objects.requireNonNull(code);
        Country office = em.find(Country.class, code);
        return Optional.ofNullable(office);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean exists(String code) {
        Objects.requireNonNull(code);
        String queryString = "select case when (count(c) > 0) " +
                "then true " +
                "else false end " +
                "from Country c where c.code = :code";
        TypedQuery<Boolean> query = em.createQuery(queryString, Boolean.class);
        query.setParameter("code", code);
        return query.getSingleResult();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void save(Country entity) {
        Objects.requireNonNull(entity);
        em.persist(entity);
    }
}
