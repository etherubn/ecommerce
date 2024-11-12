package com.comercio.demo.service;


import com.comercio.demo.dto.request.CreateOrderedDto;
import com.comercio.demo.entity.Ordered;

import java.util.List;

public interface IOrderedService extends ICrudService<Ordered,Long, CreateOrderedDto>{

}
