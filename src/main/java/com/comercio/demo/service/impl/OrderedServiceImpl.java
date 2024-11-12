package com.comercio.demo.service.impl;


import com.comercio.demo.dto.request.CreateOrderedDto;
import com.comercio.demo.entity.Ordered;
import com.comercio.demo.repository.*;
import com.comercio.demo.service.ICustomerService;
import com.comercio.demo.service.IOrderedService;
import com.comercio.demo.service.IProductService;
import com.comercio.demo.util.MapperUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;




@Service
public class OrderedServiceImpl extends CrudServiceImpl<Ordered,Long, CreateOrderedDto> implements IOrderedService  {

    private final OrderedRepository orderedRepository;

    public OrderedServiceImpl(MapperUtil mapperUtil, OrderedRepository orderedRepository) {
        super(mapperUtil);
        this.orderedRepository = orderedRepository;
    }

    @Override
    protected RepoGeneric<Ordered, Long> getRepo() {
        return orderedRepository;
    }

    @Override
    protected Class<Ordered> getEntityClass() {
        return null;
    }

    @Override
    protected Class<CreateOrderedDto> getDtoClass() {
        return null;
    }

    @Override
    protected void setEntityId(Ordered entity, Long id) {
        entity.setIdOrdered(id);
    }
}
