package com.comercio.demo.service.impl;


import com.comercio.demo.entity.Ordered;
import com.comercio.demo.repository.OrderedRepository;
import com.comercio.demo.repository.RepoGeneric;
import com.comercio.demo.service.ICustomerService;
import com.comercio.demo.service.IOrderedService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class OrderedServiceImpl extends CrudServiceImpl<Ordered,Long> implements IOrderedService  {

    private final OrderedRepository orderedRepository;
    private final ICustomerService customerService;

    @Override
    protected RepoGeneric<Ordered, Long> getRepo() {
        return orderedRepository;
    }

    @Override
    public Ordered create(Ordered ordered) {


        return null;
    }
}
