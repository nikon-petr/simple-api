package edu.nikon.simpleapi.api.office.dto.mapper;

import edu.nikon.simpleapi.api.common.embeddable.Contact;
import edu.nikon.simpleapi.api.office.domain.Office;
import edu.nikon.simpleapi.api.office.dto.OfficeDetailedDto;
import edu.nikon.simpleapi.api.office.dto.OfficeItemDto;
import edu.nikon.simpleapi.api.office.dto.SaveOfficeDto;

import java.util.function.Function;

/**
 * Util for mapping from entity to dto and in the opposite direction
 */
public class OfficeMapper {

    /**
     * Maps office entity to dto which is a list item
     *
     * @return function returning office mapped to {@link OfficeItemDto}
     * @see Office
     * @see OfficeItemDto
     */
    public static Function<Office, OfficeItemDto> mapEntityToItem() {
        return o -> new OfficeItemDto(
                o.getId(),
                o.getName(),
                o.getActive()
        );
    }

    /**
     * Maps office entity to detailed dto
     *
     * @return function returning office mapped to {@link OfficeDetailedDto}
     * @see Office
     * @see OfficeDetailedDto
     */
    public static Function<Office, OfficeDetailedDto> mapEntityToDetailed() {
        return o -> new OfficeDetailedDto(
                o.getId(),
                o.getName(),
                o.getContact().getAddress(),
                o.getContact().getPhone(),
                o.getActive()
        );
    }

    /**
     * Maps office dto to entity for saving in db
     *
     * @return function returning office dto mapped to {@link Office}
     * @see Office
     * @see SaveOfficeDto
     */
    public static Function<SaveOfficeDto, Office> mapSaveDtoToEntity() {
        return dto -> new Office.Builder()
                .setName(dto.getName())
                .setContact(new Contact(dto.getAddress(), dto.getPhone()))
                .setActive(dto.getActive())
                .build();
    }
}
