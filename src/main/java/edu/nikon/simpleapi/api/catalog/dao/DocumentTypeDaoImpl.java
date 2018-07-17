package edu.nikon.simpleapi.api.catalog.dao;

import edu.nikon.simpleapi.api.catalog.domain.DocumentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public class DocumentTypeDaoImpl implements DocumentTypeDao {

    private final EntityManager em;

    @Autowired
    public DocumentTypeDaoImpl(EntityManager em) {
        this.em = em;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<DocumentType> findAll() {
        TypedQuery<DocumentType> query = em.createQuery("select d from DocumentType d", DocumentType.class);
        return query.getResultList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<DocumentType> findById(String code) {
        Objects.requireNonNull(code);
        DocumentType office = em.find(DocumentType.class, code);
        return Optional.ofNullable(office);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean exists(String code) {
        Objects.requireNonNull(code);
        String queryString = "select case when (count(d) > 0) " +
                "then true " +
                "else false end " +
                "from DocumentType d where d.code = :code";
        TypedQuery<Boolean> query = em.createQuery(queryString, Boolean.class);
        query.setParameter("code", code);
        return query.getSingleResult();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void save(DocumentType entity) {
        Objects.requireNonNull(entity);
        em.persist(entity);
    }
}
