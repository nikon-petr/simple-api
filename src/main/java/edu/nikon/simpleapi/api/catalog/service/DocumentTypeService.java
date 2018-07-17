package edu.nikon.simpleapi.api.catalog.service;

import edu.nikon.simpleapi.api.catalog.dto.DocTypeItemDto;

import java.util.List;

/**
 * Implement business logic related to document type entity
 */
public interface DocumentTypeService {

    /**
     * Find all document types
     *
     * @return list of document types
     */
    List<DocTypeItemDto> findAll();
}
