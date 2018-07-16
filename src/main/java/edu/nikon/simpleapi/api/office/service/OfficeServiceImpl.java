package edu.nikon.simpleapi.api.office.service;

import edu.nikon.simpleapi.api.common.exception.DataConflictException;
import edu.nikon.simpleapi.api.common.exception.DataNotFoundException;
import edu.nikon.simpleapi.api.office.dao.OfficeDao;
import edu.nikon.simpleapi.api.office.domain.Office;
import edu.nikon.simpleapi.api.office.dto.FilterOfficeDto;
import edu.nikon.simpleapi.api.office.dto.OfficeDetailedDto;
import edu.nikon.simpleapi.api.office.dto.OfficeItemDto;
import edu.nikon.simpleapi.api.office.dto.SaveOfficeDto;
import edu.nikon.simpleapi.api.office.dto.UpdateOfficeDto;
import edu.nikon.simpleapi.api.office.dto.mapper.OfficeMapper;
import edu.nikon.simpleapi.api.organization.dao.OrganizationDao;
import edu.nikon.simpleapi.api.organization.domain.Organization;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * {@inheritDoc}
 */
@Service
public class OfficeServiceImpl implements OfficeService {

    private final Logger logger = LoggerFactory.getLogger(OfficeService.class);
    private final OfficeDao officeDao;
    private final OrganizationDao organizationDao;

    @Autowired
    public OfficeServiceImpl(OfficeDao officeDao,
                             OrganizationDao organizationDao) {
        this.officeDao = officeDao;
        this.organizationDao = organizationDao;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public List<OfficeItemDto> filter(FilterOfficeDto dto) {
        return officeDao.filter(dto.getOrgId(), dto.getName(), dto.getPhone(), dto.getActive()).stream()
                .map(OfficeMapper.mapEntityToItem())
                .collect(Collectors.toList());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public OfficeDetailedDto findById(long id) {
        Office office = officeDao.findById(id)
                .orElseThrow(() -> new DataNotFoundException(String.format("Office with id=%s not found", id)));

        return OfficeMapper.mapEntityToDetailed().apply(office);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public Office save(SaveOfficeDto dto) {
        Organization organization = null;

        if (dto.getOrganizationId() != null) {
            String msg = String.format("Organization with id=%s is not exists", dto.getOrganizationId());
            organization = organizationDao.findById(dto.getOrganizationId())
                    .orElseThrow(() -> new DataConflictException(msg));
        }

        Office office = OfficeMapper.mapSaveDtoToEntity().apply(dto);
        office.setOrganization(organization);
        officeDao.save(office);
        return office;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public Office update(UpdateOfficeDto dto) {
        String officeNotFound = String.format("Office with id=%s is not exists", dto.getId());
        String organizationNotFound = String.format("Organization with id=%s is not exists", dto.getOrganizationId());

        Office office = officeDao.findById(dto.getId())
                .orElseThrow(() -> new DataConflictException(officeNotFound));

        Organization organization = null;
        if (dto.getOrganizationId() != null) {
            organization = organizationDao.findById(dto.getOrganizationId())
                    .orElseThrow(() -> new DataConflictException(organizationNotFound));
        }

        office.setName(dto.getName());
        office.getContact().setAddress(dto.getAddress());
        office.getContact().setPhone(dto.getPhone());
        office.setActive(dto.getActive());
        office.setOrganization(organization);

        return office;
    }
}
