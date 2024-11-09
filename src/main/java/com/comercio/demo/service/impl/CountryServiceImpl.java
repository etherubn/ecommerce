package com.comercio.demo.service.impl;


import com.comercio.demo.entity.Country;
import com.comercio.demo.repository.CountryRepository;
import com.comercio.demo.repository.RepoGeneric;
import com.comercio.demo.service.ICountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class CountryServiceImpl extends CrudServiceImpl<Country,Long> implements ICountryService  {

    private final CountryRepository countryRepository;

    @Override
    protected RepoGeneric<Country, Long> getRepo() {
        return countryRepository;
    }

}
