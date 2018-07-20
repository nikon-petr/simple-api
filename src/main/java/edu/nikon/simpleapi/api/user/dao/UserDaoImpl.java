package edu.nikon.simpleapi.api.user.dao;

import edu.nikon.simpleapi.api.user.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
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
public class UserDaoImpl implements UserDao {

    private final EntityManager em;

    @Autowired
    public UserDaoImpl(EntityManager em) {
        this.em = em;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<User> findAll() {
        TypedQuery<User> query = em.createQuery("select u from User u", User.class);
        return query.getResultList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<User> findById(Long id) {
        Objects.requireNonNull(id);
        User organization = em.find(User.class, id);
        return Optional.ofNullable(organization);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean exists(Long id) {
        Objects.requireNonNull(id);
        String queryString = "select case when (count(u) > 0) " +
                "then true " +
                "else false end " +
                "from User u where u.id = :id";
        TypedQuery<Boolean> query = em.createQuery(queryString, Boolean.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void save(User entity) {
        Objects.requireNonNull(entity);
        em.persist(entity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<User> findByIdEager(Long id) {
        Objects.requireNonNull(id);

        String queryString = "select u from User u " +
                "join fetch u.documentType dt " +
                "join fetch u.documentData dd " +
                "join fetch u.citizenshipCountry cc " +
                "where u.id = :id";

        TypedQuery<User> query = em.createQuery(queryString, User.class);
        query.setParameter("id", id);

        User result;

        try {
            result = query.getSingleResult();
        } catch (NonUniqueResultException | NoResultException e) {
            result = null;
        }

        return Optional.ofNullable(result);
    }

    @Override
    public List<User> filter(Long officeId, String first, String second, String middle, String position, String docCode,
                             String citizenshipCode) {
        Objects.requireNonNull(officeId);
        TypedQuery<User> query = em.createQuery(buildFilterQuery(officeId, first, second, middle, position, docCode,
                                                                 citizenshipCode));
        return query.getResultList();
    }

    private CriteriaQuery<User> buildFilterQuery(Long officeId, String first, String second, String middle,
                                                 String position, String docCode, String citizenshipCode) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<User> query = em.getCriteriaBuilder().createQuery(User.class);
        Root<User> user = query.from(User.class);

        Predicate officeFilter = builder.equal(user.get("office"), officeId);
        Predicate firstNameFilter = first == null ?
                builder.conjunction() :
                builder.like(builder.lower(user.get("name").get("first")), "%" + first.toLowerCase() + "%");
        Predicate secondNameFilter = second == null ?
                builder.conjunction() :
                builder.like(builder.lower(user.get("name").get("second")), "%" + second.toLowerCase() + "%");
        Predicate middleNameFilter = middle == null ?
                builder.conjunction() :
                builder.like(builder.lower(user.get("name").get("middle")), "%" + middle.toLowerCase() + "%");
        Predicate positionFilter = position == null ?
                builder.conjunction() :
                builder.like(builder.lower(user.get("position")), "%" + position.toLowerCase() + "%");
        Predicate docCodeFilter = docCode == null ?
                builder.conjunction() :
                builder.equal(user.get("documentCode"), docCode);
        Predicate citizenshipCodeFilter = citizenshipCode == null ?
                builder.conjunction() :
                builder.equal(user.get("citizenshipCode"), citizenshipCode);

        Predicate filter = builder.and(officeFilter, firstNameFilter, secondNameFilter, middleNameFilter, positionFilter,
                                      docCodeFilter, citizenshipCodeFilter);

        return query.where(filter);
    }
}
