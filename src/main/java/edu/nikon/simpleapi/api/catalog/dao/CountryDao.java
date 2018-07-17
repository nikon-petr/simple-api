package edu.nikon.simpleapi.api.catalog.dao;

import edu.nikon.simpleapi.api.catalog.domain.Country;
import edu.nikon.simpleapi.api.common.dao.BaseDao;

/**
 * Provide an abstract interface to the database country table
 */
public interface CountryDao extends BaseDao<Country, String> {
}
