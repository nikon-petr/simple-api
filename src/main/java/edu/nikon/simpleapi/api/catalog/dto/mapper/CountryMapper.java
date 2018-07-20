package edu.nikon.simpleapi.api.catalog.dto.mapper;

import edu.nikon.simpleapi.api.catalog.domain.Country;
import edu.nikon.simpleapi.api.catalog.dto.CountryItemDto;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;
import org.springframework.stereotype.Component;

import java.util.function.Function;

/**
 * Class for configure country mapper factory
 */
@Component
public class CountryMapper extends ConfigurableMapper {

    @Override
    protected void configure(MapperFactory factory) {
        factory.classMap(Country.class, CountryItemDto.class)
                .byDefault()
                .register();
    }
}
