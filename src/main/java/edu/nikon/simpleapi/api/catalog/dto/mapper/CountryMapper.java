package edu.nikon.simpleapi.api.catalog.dto.mapper;

import edu.nikon.simpleapi.api.catalog.domain.Country;
import edu.nikon.simpleapi.api.catalog.dto.CountryItemDto;

import java.util.function.Function;

/**
 * Util for mapping from entity to dto and in the opposite direction
 */
public class CountryMapper {

    /**
     * Map entity to item dto
     *
     * @return item dto for document
     */
    public static Function<Country, CountryItemDto> mapEntityToItemDto() {
        return entity -> new CountryItemDto(entity.getCode(), entity.getName());
    }
}
