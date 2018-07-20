package edu.nikon.simpleapi.api.catalog.service;

import edu.nikon.simpleapi.api.catalog.dao.CountryDao;
import edu.nikon.simpleapi.api.catalog.dto.CountryItemDto;
import edu.nikon.simpleapi.api.catalog.dto.mapper.CountryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CountryServiceImpl implements CountryService {

    private final CountryDao countryDao;
    private final CountryMapper countryMapper;

    @Autowired
    public CountryServiceImpl(CountryDao countryDao,
                              CountryMapper countryMapper) {
        this.countryDao = countryDao;
        this.countryMapper = countryMapper;
    }

    @Override
    public List<CountryItemDto> findAll() {
        return countryMapper.mapAsList(countryDao.findAll(), CountryItemDto.class);
    }
}
