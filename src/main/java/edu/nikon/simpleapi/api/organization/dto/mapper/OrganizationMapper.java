package edu.nikon.simpleapi.api.organization.dto.mapper;

import edu.nikon.simpleapi.api.common.embeddable.Contact;
import edu.nikon.simpleapi.api.organization.domain.Organization;
import edu.nikon.simpleapi.api.organization.dto.OrganizationDetailedDto;
import edu.nikon.simpleapi.api.organization.dto.OrganizationItemDto;
import edu.nikon.simpleapi.api.organization.dto.SaveOrganizationDto;
import edu.nikon.simpleapi.api.organization.dto.UpdateOrganizationDto;

import java.util.function.Function;

/**
 * Util for mapping from entity to dto and in the opposite direction
 */
public class OrganizationMapper {

    /**
     * Maps organization entity to dto which is a list item
     *
     * @return function returning organization mapped to {@link OrganizationItemDto}
     * @see Organization
     * @see OrganizationItemDto
     */
    public static Function<Organization, OrganizationItemDto> mapEntityToItem() {
        return o -> new OrganizationItemDto(
                o.getId(),
                o.getName(),
                o.getActive()
        );
    }

    /**
     * Maps organization entity to detailed dto
     *
     * @return function returning organization mapped to {@link OrganizationDetailedDto}
     * @see Organization
     * @see OrganizationDetailedDto
     */
    public static Function<Organization, OrganizationDetailedDto> mapEntityToDetailed() {
        return o -> new OrganizationDetailedDto(
                o.getId(),
                o.getName(),
                o.getFullName(),
                o.getInn(),
                o.getKpp(),
                o.getContact().getAddress(),
                o.getContact().getPhone(),
                o.getActive()
        );
    }

    /**
     * Maps organization dto to entity for saving in db
     *
     * @return function returning organization dto mapped to {@link Organization}
     * @see Organization
     * @see SaveOrganizationDto
     */
    public static Function<SaveOrganizationDto, Organization> mapSaveDtoToEntity() {
        return dto -> new Organization(
                dto.getName(),
                dto.getFullName(),
                dto.getInn(),
                dto.getKpp(),
                new Contact(dto.getAddress(), dto.getPhone()),
                dto.isActive()
        );
    }

    public static Function<UpdateOrganizationDto, Organization> mapUpdateDtoToEntity() {
        return dto -> new Organization(
                dto.getId(),
                dto.getName(),
                dto.getFullName(),
                dto.getInn(),
                dto.getKpp(),
                new Contact(dto.getAddress(), dto.getPhone()),
                dto.isActive()
        );

    }
}
