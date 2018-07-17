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

/**
 * {@inheritDoc}
 */
@Service
public class OrganizationServiceImpl implements OrganizationService {

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
            throw new DataConflictException("Organization inn and kpp should be unique");
        }

        Organization organization = OrganizationMapper.mapSaveDtoToEntity().apply(dto);
        organizationDao.save(organization);

        return organization;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public Organization update(UpdateOrganizationDto dto) {
        String organizationNotFound = String.format("Organization with id=%s is not exists", dto.getId());

        Organization organization = organizationDao.findById(dto.getId())
                .orElseThrow(() -> new DataConflictException(organizationNotFound));

        if (!organizationDao.isUnique(dto.getId(), dto.getInn(), dto.getKpp())) {
            throw new DataConflictException("Organization inn and kpp should be unique");
        }

        organization.setName(dto.getName());
        organization.setFullName(dto.getFullName());
        organization.setInn(dto.getInn());
        organization.setKpp(dto.getKpp());
        organization.getContact().setAddress(dto.getAddress());
        organization.getContact().setPhone(dto.getPhone());
        organization.setActive(dto.isActive());

        return organization;
    }
}
