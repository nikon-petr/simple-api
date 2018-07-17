package edu.nikon.simpleapi.api.organization.dao;

import edu.nikon.simpleapi.api.common.dao.BaseDao;
import edu.nikon.simpleapi.api.organization.domain.Organization;

import java.util.List;
import java.util.Optional;

/**
 * Provide an abstract interface to the database organization table
 */
public interface OrganizationDao extends BaseDao<Organization, Long> {

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
     * @param id  id of organization to check uniqueness
     * @param inn inn of organization to check uniqueness
     * @param kpp kpp of organization to check uniqueness
     * @return is unique
     * @see Organization
     */
    boolean isUnique(long id, String inn, String kpp);

    /**
     * @param name   organization name
     * @param inn    organization inn
     * @param active organization activity state
     * @return organization with requested name, inn and activity state
     * @see Organization
     */
    List<Organization> filter(String name, String inn, Boolean active);
}
