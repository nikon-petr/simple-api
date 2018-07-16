package edu.nikon.simpleapi.api.common.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

/**
 * Interface for declaring base operations
 *
 * @param <ID> primary key type
 * @param <T>  entity type
 */
public interface BaseDao<T, ID extends Serializable> {

    /**
     * Find all entities
     *
     * @return list of found entities
     */
    List<T> findAll();

    /**
     * Find entity by id
     *
     * @param id entity id
     * @return found entity object
     */
    Optional<T> findById(ID id);

    /**
     * Returns true if entity with id is exists
     *
     * @param id entity id
     * @return is exists
     */
    boolean exists(ID id);

    /**
     * Save entity to db
     *
     * @param entity entity object
     */
    void save(T entity);

    /**
     * Update entity and return reference to updated entity
     *
     * @param entity object with data for update
     * @return updated entity
     */
    T update(T entity);

}
