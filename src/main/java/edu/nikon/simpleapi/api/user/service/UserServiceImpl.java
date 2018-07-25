package edu.nikon.simpleapi.api.user.service;

import edu.nikon.simpleapi.api.catalog.dao.CountryDao;
import edu.nikon.simpleapi.api.catalog.dao.DocumentTypeDao;
import edu.nikon.simpleapi.api.catalog.domain.Country;
import edu.nikon.simpleapi.api.catalog.domain.DocumentType;
import edu.nikon.simpleapi.api.common.exception.DataConflictException;
import edu.nikon.simpleapi.api.common.exception.DataNotFoundException;
import edu.nikon.simpleapi.api.office.dao.OfficeDao;
import edu.nikon.simpleapi.api.office.domain.Office;
import edu.nikon.simpleapi.api.user.dao.DocumentDataDao;
import edu.nikon.simpleapi.api.user.dao.UserDao;
import edu.nikon.simpleapi.api.user.domain.DocumentData;
import edu.nikon.simpleapi.api.user.domain.User;
import edu.nikon.simpleapi.api.user.dto.FilterUserDto;
import edu.nikon.simpleapi.api.user.dto.SaveUserDto;
import edu.nikon.simpleapi.api.user.dto.UpdateUserDto;
import edu.nikon.simpleapi.api.user.dto.UserDetailedDto;
import edu.nikon.simpleapi.api.user.dto.UserItemDto;
import edu.nikon.simpleapi.api.user.dto.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * {@inheritDoc}
 */
@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;
    private final OfficeDao officeDao;
    private final DocumentTypeDao documentTypeDao;
    private final DocumentDataDao documentDataDao;
    private final CountryDao countryDao;
    private final UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserDao userDao, OfficeDao officeDao,
                           DocumentTypeDao documentTypeDao,
                           DocumentDataDao documentDataDao, CountryDao countryDao,
                           UserMapper userMapper) {
        this.userDao = userDao;
        this.officeDao = officeDao;
        this.documentTypeDao = documentTypeDao;
        this.documentDataDao = documentDataDao;
        this.countryDao = countryDao;
        this.userMapper = userMapper;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public List<UserItemDto> filter(FilterUserDto dto) {
        List<User> users = userDao.filter(dto.getOfficeId(),
                                          dto.getFirstName(),
                                          dto.getSecondName(),
                                          dto.getMiddleName(),
                                          dto.getPosition(),
                                          dto.getDocCode(),
                                          dto.getCitizenshipCode());
        return userMapper.mapAsList(users, UserItemDto.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public UserDetailedDto findById(long id) {
        String notFound = String.format("User with id=%s is not exist", id);
        User user = userDao.findByIdEager(id)
                .orElseThrow(() -> new DataNotFoundException(notFound));
        return userMapper.map(user, UserDetailedDto.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public User save(SaveUserDto dto) {
        Office office = null;
        DocumentType documentType = null;
        DocumentData documentData = null;
        Country country = null;

        if (dto.getOfficeId() != null) {
            String msg = String.format("Office with id=%s is not exists", dto.getOfficeId());
            office = officeDao.findById(dto.getOfficeId())
                    .orElseThrow(() -> new DataConflictException(msg));
        }

        if (dto.getDocCode() != null) {
            String msg = String.format("Document code %s is incorrect", dto.getDocCode());
            documentType = documentTypeDao.findById(dto.getDocCode())
                    .orElseThrow(() -> new DataConflictException(msg));
        }

        if (dto.getDocDate() != null || dto.getDocNumber() != null) {
            if (dto.getDocNumber() != null && !documentDataDao.isUnique(dto.getDocNumber())) {
                throw new DataConflictException("Document number should be unique");
            }
            documentData = new DocumentData(dto.getDocNumber(), dto.getDocDate());
            documentData.setDocumentType(documentType);
        }

        if (dto.getCitizenshipCode() != null) {
            String msg = String.format("Citizenship country code %s is incorrect", dto.getCitizenshipCode());
            country = countryDao.findById(dto.getCitizenshipCode())
                    .orElseThrow(() -> new DataConflictException(msg));
        }

        User user = userMapper.map(dto, User.class);
        user.setOffice(office);
        user.attachDocumentData(documentData);
        user.setCitizenshipCountry(country);
        userDao.save(user);

        return user;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public User update(UpdateUserDto dto) {
        Office office = null;
        DocumentType documentType = null;
        Country country = null;

        if (dto.getOfficeId() != null) {
            String msg = String.format("Office with id=%s is not exists", dto.getOfficeId());
            office = officeDao.findById(dto.getOfficeId())
                    .orElseThrow(() -> new DataConflictException(msg));
        }

        if (dto.getDocCode() != null) {
            String msg = String.format("Document code %s is incorrect", dto.getDocCode());
            documentType = documentTypeDao.findById(dto.getDocCode())
                    .orElseThrow(() -> new DataConflictException(msg));
        }

        if (dto.getCitizenshipCode() != null) {
            String msg = String.format("Citizenship country code %s is incorrect", dto.getCitizenshipCode());
            country = countryDao.findById(dto.getCitizenshipCode())
                    .orElseThrow(() -> new DataConflictException(msg));
        }

        String userNotFound = String.format("Office with id=%s is not exists", dto.getId());
        User user = userDao.findById(dto.getId())
                .orElseThrow(() -> new DataNotFoundException(userNotFound));

        userMapper.map(dto, user);

        if (dto.getDocDate() != null || dto.getDocNumber() != null) {
            if (dto.getDocNumber() != null && !documentDataDao.isUnique(dto.getId(), dto.getDocNumber())) {
                throw new DataConflictException("Document number should be unique");
            }
            if (user.getDocumentData() == null) {
                DocumentData documentData = new DocumentData(dto.getDocNumber(), dto.getDocDate());
                documentData.setDocumentType(documentType);
                user.attachDocumentData(documentData);
            } else {
                user.getDocumentData().setDocumentType(documentType);
                user.getDocumentData().setNumber(dto.getDocNumber());
                user.getDocumentData().setDate(dto.getDocDate());
            }
        }

        user.setCitizenshipCountry(country);
        user.setOffice(office);

        return user;
    }
}
