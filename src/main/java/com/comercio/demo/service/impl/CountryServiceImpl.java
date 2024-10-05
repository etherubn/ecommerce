package com.comercio.demo.service.impl;

import com.comercio.demo.dto.request.CreateCountryDto;
import com.comercio.demo.dto.response.ResponseCountryDto;
import com.comercio.demo.entity.Country;
import com.comercio.demo.handlerException.IdWrongValueException;
import com.comercio.demo.handlerException.NotFoundException;
import com.comercio.demo.handlerException.RepeatedException;
import com.comercio.demo.repository.CountryRepository;
import com.comercio.demo.service.CountryService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CountryServiceImpl implements CountryService {
    private final CountryRepository countryRepository;
    private ModelMapper modelMapper = new ModelMapper();


    public CountryServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }


    @Transactional(readOnly = true)
    @Override
    public List<ResponseCountryDto> findAll() {
        return countryRepository.findAll().stream()
                .map(country -> modelMapper.map(country,ResponseCountryDto.class))
                .toList();
    }

    @Transactional
    @Override
    public ResponseCountryDto create(CreateCountryDto createCountryDto) {
        if (countryRepository.existsByCountryCode(createCountryDto.getCountryCode())){
            throw new RepeatedException("Country");
        }
        Country country = modelMapper.map(createCountryDto, Country.class);
        Country countrySaved = countryRepository.save(country);
        return modelMapper.map(countrySaved,ResponseCountryDto.class);
    }

    @Transactional
    @Override
    public ResponseCountryDto update(Long id, CreateCountryDto createCountryDto) {
        existedId(id);

        if (!countryRepository.existsById(id)){
            throw new NotFoundException("Country",id);
        }
        
        if (countryRepository.existsByCountryCode(createCountryDto.getCountryCode())){
            throw new RepeatedException("Country");
        }
        
        Country country = modelMapper.map(createCountryDto,Country.class);
        country.setId(id);
        Country countryUpdated= countryRepository.save(country);
        
        
        return modelMapper.map(countryUpdated,ResponseCountryDto.class);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        existedId(id);
        Country country = countryRepository.findById(id).orElseThrow(()-> new NotFoundException("Country",id));
        countryRepository.delete(country);
    }

    @Transactional(readOnly = true)
    @Override
    public ResponseCountryDto getById(Long id) {
        existedId(id);
        Country country = countryRepository.findById(id).orElseThrow(()-> new NotFoundException("Country",id));
        return modelMapper.map(country,ResponseCountryDto.class);
    }



    private static void existedId(Long id) {
        if (id ==null || id <=0){
            throw new IdWrongValueException("Country", id);
        }
    }
}
