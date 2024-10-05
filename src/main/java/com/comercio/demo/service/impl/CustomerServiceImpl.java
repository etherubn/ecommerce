package com.comercio.demo.service.impl;

import com.comercio.demo.dto.request.CreateCustomerDto;
import com.comercio.demo.dto.response.ResponseCountryDto;
import com.comercio.demo.dto.response.ResponseCustomerDto;
import com.comercio.demo.entity.Country;
import com.comercio.demo.entity.Customer;
import com.comercio.demo.handlerException.IdWrongValueException;
import com.comercio.demo.handlerException.NotFoundException;
import com.comercio.demo.handlerException.RepeatedException;
import com.comercio.demo.repository.CountryRepository;
import com.comercio.demo.repository.CustomerRepository;
import com.comercio.demo.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final CountryRepository countryRepository;
    private ModelMapper modelMapper = new ModelMapper();

    @Transactional(readOnly = true)
    @Override
    public List<ResponseCustomerDto> findAll() {
        return customerRepository.findAll().stream()
                .map(customer -> {
                    ResponseCustomerDto dto = modelMapper.map(customer,ResponseCustomerDto.class);
                    if (customer.getCountry()!=null){
                        ResponseCountryDto responseCountryDto = modelMapper.map(customer.getCountry(), ResponseCountryDto.class);
                        dto.setResponseCountryDto(responseCountryDto);
                    }
                    return dto;
                })
                .toList();
    }

    @Override
    @Transactional
    public ResponseCustomerDto create(CreateCustomerDto createCustomerDto) {
        //Viendo si existe el pais agregado
        Country country = countryRepository.findCountry(createCustomerDto.getCountryId()).orElseThrow(()->
                new NotFoundException("Country",createCustomerDto.getCountryId())
        );
        //Viendo si ya existe ese usaurio
        if (customerRepository.existsByDni(createCustomerDto.getDni())){
            throw new RepeatedException("Customer");
        }

        //Verificar los campos únicos
        String existError = existByUsernameOrEmail(createCustomerDto.getUsername(), createCustomerDto.getEmail());
        if (!existError.isEmpty()) {
            throw new DataIntegrityViolationException(existError);
        }

        modelMapper.typeMap(CreateCustomerDto.class, Customer.class).addMappings(mapper -> mapper.skip(Customer::setId));
        Customer customer = modelMapper.map(createCustomerDto,Customer.class);
        //Seteamos el valor de pais a customer
        customer.setCountry(country);


        //Guardamos el customer a la base de datos
        Customer customerSaved = customerRepository.save(customer);

        //Como queremos un ResponseCustomerDto y necesitamos asociarle el pais además hacemos 2 mapeos
        ResponseCountryDto responseCountryDto = modelMapper.map(country,ResponseCountryDto.class);
        ResponseCustomerDto responseCustomerDto = modelMapper.map(customerSaved,ResponseCustomerDto.class);
        responseCustomerDto.setResponseCountryDto(responseCountryDto);

        return responseCustomerDto;
    }

    @Transactional
    @Override
    public ResponseCustomerDto update(Long id, CreateCustomerDto createCustomerDto) {
        if (!customerRepository.existsById(id)){
            throw new IdWrongValueException("Customer",id);
        }

        List<String> duplicates = isUsedForAnotherCustomer(createCustomerDto.getUsername(), createCustomerDto.getEmail(), createCustomerDto.getDni(), id);
        if (!duplicates.isEmpty()){
            String fields = String.join(",",duplicates);
            throw new DuplicateKeyException(fields);
        }

        Country country = countryRepository.findById(createCustomerDto.getCountryId()).orElseThrow(()-> new NotFoundException("Country",createCustomerDto.getCountryId()));

        Customer customer = modelMapper.map(createCustomerDto,Customer.class);
        customer.setId(id);

        Customer customerUpdated = customerRepository.save(customer);
        ResponseCustomerDto responseCustomerDto = modelMapper.map(customerUpdated, ResponseCustomerDto.class);
        ResponseCountryDto responseCountryDto = modelMapper.map(country,ResponseCountryDto.class);
        responseCustomerDto.setResponseCountryDto(responseCountryDto);
        return responseCustomerDto;
    }

    @Transactional
    @Override
    public void delete(Long id) {
        if (!customerRepository.existsById(id)){
            throw new IdWrongValueException("Customer",id);
        }

        Customer customer = customerRepository.findById(id).get();
        customerRepository.delete(customer);
    }

    @Transactional(readOnly = true)
    @Override
    public ResponseCustomerDto getById(Long id) {
        if (!customerRepository.existsById(id)){
            throw new NotFoundException("Customer",id);
        }

        Customer customer = customerRepository.findById(id).get();
        ResponseCustomerDto responseCustomerDto = modelMapper.map(customer, ResponseCustomerDto.class);
        ResponseCountryDto responseCountryDto = modelMapper.map(customer.getCountry(),ResponseCountryDto.class);
        responseCustomerDto.setResponseCountryDto(responseCountryDto);

        return responseCustomerDto;
    }

    private List<String> isUsedForAnotherCustomer(String username,String email, String dni,Long customerInicial){
        List<String> duplicates = new ArrayList<>();

        boolean existEmail = customerRepository.findByEmail(email).stream()
                .anyMatch(customer -> !customer.getId().equals(customerInicial));

        if (existEmail){
            duplicates.add("email");
        }

        boolean existUsername = customerRepository.findByUsername(username)
                .stream().anyMatch(customer-> !customer.getId().equals(customerInicial));

        if (existUsername){
            duplicates.add("username");
        }
        boolean existDni = customerRepository.findByDni(dni)
                .stream().anyMatch(customer-> !customer.getId().equals(customerInicial));

        if (existDni){
            duplicates.add("dni");
        }
        return duplicates;
    }

    private String existByUsernameOrEmail(String username,String email){
        String error = "";

        if (customerRepository.existsByEmail(email)){
            error+="email ";
        }

        if (customerRepository.existsByUsername(username)){
            error+="username";
        }

        return error;
    }
}

