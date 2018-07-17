package edu.nikon.simpleapi.api.organization.service;

import edu.nikon.simpleapi.api.common.exception.DataNotFoundException;
import edu.nikon.simpleapi.api.organization.domain.Organization;
import edu.nikon.simpleapi.api.organization.dto.FilterOrganizationDto;
import edu.nikon.simpleapi.api.organization.dto.OrganizationDetailedDto;
import edu.nikon.simpleapi.api.organization.dto.OrganizationItemDto;
import edu.nikon.simpleapi.api.organization.dto.SaveOrganizationDto;
import edu.nikon.simpleapi.api.organization.dto.UpdateOrganizationDto;

import java.util.List;

/**
 * Service used to get organization data from the database
 */
public interface OrganizationService {

    /**
     * Filter organizations by parameters defined in {@link FilterOrganizationDto}
     *
     * @param dto organization filter data access object
     * @return List of filtered organizations
     * @see FilterOrganizationDto
     * @see OrganizationItemDto
     */
    List<OrganizationItemDto> filter(FilterOrganizationDto dto);

    /**
     * Finds organization with requested id. If not found throws {@link DataNotFoundException}
     *
     * @param id of requested organization
     * @return dto of requested organization
     * @see OrganizationDetailedDto
     */
    OrganizationDetailedDto findById(long id);

    /**
     * Save organization to db
     *
     * @param dto for save organization in db
     * @return saved organization entity
     * @see Organization
     * @see SaveOrganizationDto
     */
    Organization save(SaveOrganizationDto dto);

    /**
     * Updates organization in db
     *
     * @param dto for update organization in db
     * @return updated organization entity
     * @see Organization
     * @see UpdateOrganizationDto
     */
    Organization update(UpdateOrganizationDto dto);
}
