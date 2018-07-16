package edu.nikon.simpleapi.api.office.service;

import edu.nikon.simpleapi.api.common.exception.DataNotFoundException;
import edu.nikon.simpleapi.api.office.domain.Office;
import edu.nikon.simpleapi.api.office.dto.FilterOfficeDto;
import edu.nikon.simpleapi.api.office.dto.OfficeDetailedDto;
import edu.nikon.simpleapi.api.office.dto.OfficeItemDto;
import edu.nikon.simpleapi.api.office.dto.SaveOfficeDto;
import edu.nikon.simpleapi.api.office.dto.UpdateOfficeDto;

import java.util.List;

/**
 * Service used to get data from th database
 */
public interface OfficeService {

    /**
     * Filter offices by parameters defined in {@link FilterOfficeDto}
     *
     * @param dto office filter data access object
     * @return List of filtered offices
     * @see FilterOfficeDto
     * @see OfficeItemDto
     */
    List<OfficeItemDto> filter(FilterOfficeDto dto);

    /**
     * Finds office with requested id. If not found throws {@link DataNotFoundException}
     *
     * @param id of requested office
     * @return dto of requested office
     * @see OfficeDetailedDto
     */
    OfficeDetailedDto findById(long id);


    /**
     * Save office to db
     *
     * @param dto for save office in db
     * @return saved office entity
     * @see Office
     * @see SaveOfficeDto
     */
    Office save(SaveOfficeDto dto);

    /**
     * Updates office in db
     *
     * @param dto for update office in db
     * @return updated office entity
     * @see Office
     * @see UpdateOfficeDto
     */
    Office update(UpdateOfficeDto dto);
}
