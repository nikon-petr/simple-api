package edu.nikon.simpleapi.api.office.dto.mapper;

import edu.nikon.simpleapi.api.office.domain.Office;
import edu.nikon.simpleapi.api.office.dto.OfficeDetailedDto;
import edu.nikon.simpleapi.api.office.dto.OfficeItemDto;
import edu.nikon.simpleapi.api.office.dto.SaveOfficeDto;
import edu.nikon.simpleapi.api.office.dto.UpdateOfficeDto;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;
import org.springframework.stereotype.Component;

/**
 * Class for configure office mapper factory
 */
@Component
public class OfficeMapper extends ConfigurableMapper {

    @Override
    protected void configure(MapperFactory factory) {
        factory.classMap(Office.class, OfficeItemDto.class)
                .byDefault()
                .register();

        factory.classMap(Office.class, OfficeDetailedDto.class)
                .field("contact.address", "address")
                .field("contact.phone", "phone")
                .byDefault()
                .register();

        factory.classMap(Office.class, SaveOfficeDto.class)
                .field("contact.address", "address")
                .field("contact.phone", "phone")
                .byDefault()
                .register();

        factory.classMap(Office.class, UpdateOfficeDto.class)
                .field("contact.address", "address")
                .field("contact.phone", "phone")
                .byDefault()
                .register();
    }
}
