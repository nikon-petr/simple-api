package edu.nikon.simpleapi.api.office.dao;

import edu.nikon.simpleapi.api.common.dao.BaseDao;
import edu.nikon.simpleapi.api.office.domain.Office;
import edu.nikon.simpleapi.api.organization.domain.Organization;

import java.util.List;

/**
 * Provide an abstract interface to the database office table
 */
public interface OfficeDao extends BaseDao<Office, Long> {

    /**
     * Filter offices with specified parameters
     *
     * @param orgId  organization id
     * @param name   office name
     * @param phone  office inn
     * @param active office activity state
     * @return office with requested name, inn and activity state
     * @see Organization
     */
    List<Office> filter(Long orgId, String name, String phone, Boolean active);
}
