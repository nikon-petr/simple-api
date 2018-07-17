package edu.nikon.simpleapi.api.catalog.service;

import edu.nikon.simpleapi.api.catalog.dto.CountryItemDto;

import java.util.List;

/**
 * Implement business logic related to country entity
 */
public interface CountryService {

    /**
     * Find all countries
     *
     * @return list of countries
     */
    List<CountryItemDto> findAll();
}
