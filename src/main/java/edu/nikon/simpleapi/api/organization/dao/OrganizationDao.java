package edu.nikon.simpleapi.api.organization.dao;

import edu.nikon.simpleapi.api.organization.domain.Organization;

import java.util.List;
import java.util.Optional;

/**
 * Data access object for {@link Organization} entity
 */
public interface OrganizationDao {

    /**
     * Returns all organizations
     *
     * @return all organizations from db
     * @see Organization
     */
    List<Organization> findAll();

    /**
     * Returns true if organization with specified id is exists
     *
     * @param id organization id in db
     * @return is exists
     * @see Organization
     */
    boolean exists(long id);

    /**
     * Returns the organization with the requested id
     *
     * @param id organization id in db
     * @return optional of organization with the requested id
     * @see Organization
     */
    Optional<Organization> findById(long id);

    /**
     * Returns true if pair of inn and kpp is unique
     *
     * @param inn inn of organization to check uniqueness
     * @param kpp kpp of organization to check uniqueness
     * @return is unique
     * @see Organization
     */
    boolean isUnique(String inn, String kpp);

    /**
     * Returns true if pair of inn and kpp is unique excluding organization with specified id
     *
     * @param id id of organization to check uniqueness
     * @param inn inn of organization to check uniqueness
     * @param kpp kpp of organization to check uniqueness
     * @return is unique
     * @see Organization
     */
    boolean isUnique(long id, String inn, String kpp);

    /**
     * @param name organization name
     * @param inn organization inn
     * @param active organization activity state
     * @return organization with requested name, inn and activity state
     * @see Organization
     */
    List<Organization> filter(String name, String inn, Boolean active);

    /**
     * Saves organization to db
     *
     * @param o organization entity for saving
     * @see Organization
     */
    void save(Organization o);


    /**
     * Updates organization in db
     *
     * @param o organization entity for updating
     * @see Organization
     */
    Organization update(Organization o);


}
