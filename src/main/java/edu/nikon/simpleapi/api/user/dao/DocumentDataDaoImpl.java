package edu.nikon.simpleapi.api.user.dao;

import edu.nikon.simpleapi.api.user.domain.DocumentData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * {@inheritDoc}
 */
@Repository
public class DocumentDataDaoImpl implements DocumentDataDao {

    private final EntityManager em;

    @Autowired
    public DocumentDataDaoImpl(EntityManager em) {
        this.em = em;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public List<DocumentData> findAll() {
        TypedQuery<DocumentData> query = em.createQuery("select d from DocumentData d", DocumentData.class);
        return query.getResultList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<DocumentData> findById(Long id) {
        Objects.requireNonNull(id);
        DocumentData organization = em.find(DocumentData.class, id);
        return Optional.ofNullable(organization);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public boolean exists(Long id) {
        Objects.requireNonNull(id);
        String queryString = "select case when (count(d) > 0) " +
                "then true " +
                "else false end " +
                "from DocumentData d where d.userId = :id";
        TypedQuery<Boolean> query = em.createQuery(queryString, Boolean.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void save(DocumentData entity) {
        Objects.requireNonNull(entity);
        em.persist(entity);
    }

    @Override
    public boolean isUnique(String number) {
        Objects.requireNonNull(number);
        String queryString = "select case when (count(d) = 0) " +
                "then true " +
                "else false end " +
                "from DocumentData d where d.number = :number";
        TypedQuery<Boolean> query = em.createQuery(queryString, Boolean.class);
        query.setParameter("number", number);
        return query.getSingleResult();
    }

    @Override
    public boolean isUnique(Long userId, String number) {
        Objects.requireNonNull(number);
        String queryString = "select case when (count(d) = 0) " +
                "then true " +
                "else false end " +
                "from DocumentData d " +
                "where d.number = :number and " +
                "not d.userId = :userId";
        TypedQuery<Boolean> query = em.createQuery(queryString, Boolean.class);
        query.setParameter("number", number);
        query.setParameter("userId", userId);
        return query.getSingleResult();
    }
}
