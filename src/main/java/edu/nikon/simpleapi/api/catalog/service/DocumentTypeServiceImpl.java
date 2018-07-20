package edu.nikon.simpleapi.api.catalog.service;

import edu.nikon.simpleapi.api.catalog.dao.DocumentTypeDao;
import edu.nikon.simpleapi.api.catalog.dto.DocTypeItemDto;
import edu.nikon.simpleapi.api.catalog.dto.mapper.DocumentTypeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * {@inheritDoc}
 */
@Service
public class DocumentTypeServiceImpl implements DocumentTypeService {

    private final DocumentTypeDao documentTypeDao;
    private final DocumentTypeMapper documentTypeMapper;

    @Autowired
    public DocumentTypeServiceImpl(DocumentTypeDao documentTypeDao,
                                   DocumentTypeMapper documentTypeMapper) {
        this.documentTypeDao = documentTypeDao;
        this.documentTypeMapper = documentTypeMapper;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<DocTypeItemDto> findAll() {
        return documentTypeMapper.mapAsList(documentTypeDao.findAll(), DocTypeItemDto.class);
    }
}
