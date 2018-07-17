package edu.nikon.simpleapi.api.catalog.service;

import edu.nikon.simpleapi.api.catalog.dao.CountryDao;
import edu.nikon.simpleapi.api.catalog.dto.CountryItemDto;
import edu.nikon.simpleapi.api.catalog.dto.mapper.CountryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CountryServiceImpl implements CountryService {

    private final CountryDao countryDao;

    @Autowired
    public CountryServiceImpl(CountryDao countryDao) {
        this.countryDao = countryDao;
    }

    @Override
    public List<CountryItemDto> findAll() {
        return countryDao.findAll().stream()
                .map(CountryMapper.mapEntityToItemDto())
                .collect(Collectors.toList());
    }
}
