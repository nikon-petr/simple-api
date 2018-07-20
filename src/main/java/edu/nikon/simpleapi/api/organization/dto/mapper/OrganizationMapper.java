package edu.nikon.simpleapi.api.organization.dto.mapper;

import edu.nikon.simpleapi.api.organization.domain.Organization;
import edu.nikon.simpleapi.api.organization.dto.OrganizationDetailedDto;
import edu.nikon.simpleapi.api.organization.dto.OrganizationItemDto;
import edu.nikon.simpleapi.api.organization.dto.SaveOrganizationDto;
import edu.nikon.simpleapi.api.organization.dto.UpdateOrganizationDto;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;
import org.springframework.stereotype.Component;

/**
 * Class for configure organization mapper factory
 */
@Component
public class OrganizationMapper extends ConfigurableMapper {

    @Override
    protected void configure(MapperFactory factory) {
        factory.classMap(Organization.class, OrganizationItemDto.class)
                .byDefault()
                .register();

        factory.classMap(Organization.class, OrganizationDetailedDto.class)
                .field("contact.address", "address")
                .field("contact.phone", "phone")
                .byDefault()
                .register();

        factory.classMap(Organization.class, SaveOrganizationDto.class)
                .field("contact.address", "address")
                .field("contact.phone", "phone")
                .byDefault()
                .register();

        factory.classMap(Organization.class, UpdateOrganizationDto.class)
                .field("contact.address", "address")
                .field("contact.phone", "phone")
                .byDefault()
                .register();
    }
}
