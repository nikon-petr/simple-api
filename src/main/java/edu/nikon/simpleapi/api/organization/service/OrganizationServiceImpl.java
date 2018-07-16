package edu.nikon.simpleapi.api.organization.service;

import edu.nikon.simpleapi.api.common.exception.DataConflictException;
import edu.nikon.simpleapi.api.common.exception.DataNotFoundException;
import edu.nikon.simpleapi.api.organization.OrganizationController;
import edu.nikon.simpleapi.api.organization.dao.OrganizationDao;
import edu.nikon.simpleapi.api.organization.domain.Organization;
import edu.nikon.simpleapi.api.organization.dto.FilterOrganizationDto;
import edu.nikon.simpleapi.api.organization.dto.OrganizationDetailedDto;
import edu.nikon.simpleapi.api.organization.dto.OrganizationItemDto;
import edu.nikon.simpleapi.api.organization.dto.SaveOrganizationDto;
import edu.nikon.simpleapi.api.organization.dto.UpdateOrganizationDto;
import edu.nikon.simpleapi.api.organization.dto.mapper.OrganizationMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrganizationServiceImpl implements OrganizationService {

    private final Logger logger = LoggerFactory.getLogger(OrganizationController.class);

    private final OrganizationDao organizationDao;

    public OrganizationServiceImpl(OrganizationDao organizationDao) {
        this.organizationDao = organizationDao;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public List<OrganizationItemDto> filter(FilterOrganizationDto dto) {
        return organizationDao.filter(dto.getName(), dto.getInn(), dto.isActive()).stream()
                .map(OrganizationMapper.mapEntityToItem())
                .collect(Collectors.toList());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public OrganizationDetailedDto findById(long id) {
        Organization organization = organizationDao
                .findById(id)
                .orElseThrow(() -> new DataNotFoundException(String.format("Organization with id=%s not found", id)));

        return OrganizationMapper.mapEntityToDetailed().apply(organization);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public Organization save(SaveOrganizationDto dto) {
        if (!organizationDao.isUnique(dto.getInn(), dto.getKpp())) {
            String message = String.format("Organization with inn=%s and kpp=%s already exists", dto.getInn(), dto.getKpp());
            throw new DataConflictException(message);
        }

        Organization organization = OrganizationMapper.mapSaveDtoToEntity().apply(dto);
        organizationDao.save(organization);

        logger.debug("Entity saved {}", organization);

        return organization;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public Organization update(UpdateOrganizationDto dto) {
        if (!organizationDao.exists(dto.getId())) {
            String message = String.format("Organization with id=%s is not exists", dto.getId());
            throw new DataConflictException(message);
        }


        if (!organizationDao.isUnique(dto.getId(), dto.getInn(), dto.getKpp())) {
            throw new DataConflictException("Organization inn and kpp should be unique");
        }

        Organization organization = OrganizationMapper.mapUpdateDtoToEntity().apply(dto);
        organization = organizationDao.update(organization);

        return organization;
    }
}
