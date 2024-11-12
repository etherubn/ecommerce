package com.comercio.demo.service;

import com.comercio.demo.dto.request.CreateCountryDto;
import com.comercio.demo.dto.response.ResponseCountryDto;
import com.comercio.demo.entity.Category;
import com.comercio.demo.entity.Country;

import java.util.List;

public interface ICountryService extends ICrudService<Country,Long,CreateCountryDto>  {


}
