package edu.nikon.simpleapi.api.catalog.dto.mapper;

import edu.nikon.simpleapi.api.catalog.domain.DocumentType;
import edu.nikon.simpleapi.api.catalog.dto.DocTypeItemDto;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;
import org.springframework.stereotype.Component;

import java.util.function.Function;

/**
 * Class for configure document type mapper factory
 */
@Component
public class DocumentTypeMapper extends ConfigurableMapper {

    @Override
    protected void configure(MapperFactory factory) {
        factory.classMap(DocumentType.class, DocTypeItemDto.class)
                .byDefault()
                .register();
    }
}
