package com.comercio.demo.service.impl;


import com.comercio.demo.dto.request.CreateCountryDto;
import com.comercio.demo.entity.Country;
import com.comercio.demo.repository.CountryRepository;
import com.comercio.demo.repository.RepoGeneric;
import com.comercio.demo.service.ICountryService;
import com.comercio.demo.util.MapperUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;



@Service
public class CountryServiceImpl extends CrudServiceImpl<Country,Long, CreateCountryDto> implements ICountryService  {

    private final CountryRepository countryRepository;

    public CountryServiceImpl(MapperUtil mapperUtil, CountryRepository countryRepository) {
        super(mapperUtil);
        this.countryRepository = countryRepository;
    }

    @Override
    protected RepoGeneric<Country, Long> getRepo() {
        return countryRepository;
    }

    @Override
    protected Class<Country> getEntityClass() {
        return Country.class;
    }

    @Override
    protected Class<CreateCountryDto> getDtoClass() {
        return CreateCountryDto.class;
    }

    @Override
    protected void setEntityId(Country entity, Long id) {
        entity.setIdCountry(id);
    }
}
