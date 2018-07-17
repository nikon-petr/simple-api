package edu.nikon.simpleapi.api.catalog.dto.mapper;

import edu.nikon.simpleapi.api.catalog.domain.DocumentType;
import edu.nikon.simpleapi.api.catalog.dto.DocTypeItemDto;

import java.util.function.Function;

/**
 * Util for mapping from entity to dto and in the opposite direction
 */
public class DocumentTypeMapper {

    /**
     * Map entity to item dto
     *
     * @return item dto for document type
     */
    public static Function<DocumentType, DocTypeItemDto> mapEntityToItemDto() {
        return entity -> new DocTypeItemDto(entity.getCode(), entity.getName());
    }
}
