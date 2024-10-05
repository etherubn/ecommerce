package com.comercio.demo.service.impl;

import com.comercio.demo.dto.request.CreateOrderDto;
import com.comercio.demo.dto.request.CreateOrderProductDto;
import com.comercio.demo.dto.response.ResponseOrderDto;
import com.comercio.demo.dto.response.ResponseSelling;
import com.comercio.demo.entity.Customer;
import com.comercio.demo.entity.OrderProduct;
import com.comercio.demo.entity.Ordered;
import com.comercio.demo.entity.Product;
import com.comercio.demo.handlerException.*;
import com.comercio.demo.repository.CustomerRepository;
import com.comercio.demo.repository.OrderProductRepository;
import com.comercio.demo.repository.OrderRepository;
import com.comercio.demo.repository.ProductRepository;
import com.comercio.demo.service.OrderService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final CustomerRepository customerRepository;
    private final OrderProductRepository orderProductRepository;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;

    public OrderServiceImpl(CustomerRepository customerRepository, OrderProductRepository orderProductRepository, ProductRepository productRepository, OrderRepository orderRepository) {
        this.customerRepository = customerRepository;
        this.orderProductRepository = orderProductRepository;
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
    }

    @Transactional(readOnly = true)
    @Override
    public List<ResponseOrderDto> findAll() {
        List<Ordered> orderedList = orderRepository.findAll();
        List<ResponseOrderDto> responseOrderDtos = new ArrayList<>();

        orderedList.forEach(ordered -> {
            List<ResponseSelling> responseSellingList = new ArrayList<>();
            ResponseOrderDto responseOrderDto = new ResponseOrderDto();
            responseOrderDto.setId(ordered.getId());
            responseOrderDto.setOrderedDate(ordered.getOrderDate());
            responseOrderDto.setUsername(ordered.getCustomer().getUsername());
            responseOrderDto.setEmail(ordered.getCustomer().getEmail());
            responseOrderDto.setTotal(ordered.getTotal());

            ordered.getOrderProducts()
                    .forEach(orderProduct -> {
                        ResponseSelling responseSelling = new ResponseSelling();
                        Product product = orderProduct.getProduct();
                        responseSelling.setProduct(product.getName());
                        responseSelling.setPrice(product.getPrice());
                        responseSelling.setSubtotal(orderProduct.getSubtotal());
                        responseSelling.setQuantity(orderProduct.getQuantity());
                        responseSellingList.add(responseSelling);
                    });
            responseOrderDto.setResponseSellings(responseSellingList);
            responseOrderDtos.add(responseOrderDto);
        });
        return responseOrderDtos;
    }

    @Transactional
    @Override
    public ResponseOrderDto create(CreateOrderDto createOrderDto) {
       Customer customer = customerRepository.findById(createOrderDto.getIdCustomer())
               .orElseThrow(()-> new NotFoundException("Customer",createOrderDto.getIdCustomer()));


       BigDecimal total = BigDecimal.ZERO;

       List<OrderProduct> orderProducts = new ArrayList<>();
       List<ResponseSelling> responseSellingList = new ArrayList<>();
       for (CreateOrderProductDto createOrderProductDto: createOrderDto.getOrderProducts()){
           Product product = productRepository.findById(createOrderProductDto.getProduct())
                   .orElseThrow(()-> new NotFoundException("Product",createOrderProductDto.getProduct()));

           //Verificando stock
           if (product.getStock()<createOrderProductDto.getQuantity()){
               throw new NotStockException(product.getStock(),createOrderProductDto.getQuantity(), product.getName());
           }

           //Calculando nuevo stock
           product.setStock(product.getStock()-createOrderProductDto.getQuantity());
           productRepository.save(product);

           OrderProduct orderProduct = new OrderProduct();
           orderProduct.setQuantity(createOrderProductDto.getQuantity());
           orderProduct.setProduct(product);

           BigDecimal subtotal = BigDecimal.valueOf(createOrderProductDto.getQuantity()).multiply(product.getPrice()).setScale(2);
           orderProduct.setSubtotal(subtotal);

           orderProducts.add(orderProduct);

           total = total.add(subtotal);

           ResponseSelling responseSelling = new ResponseSelling();
           responseSelling.setProduct(product.getName());
           responseSelling.setPrice(product.getPrice());
           responseSelling.setQuantity(createOrderProductDto.getQuantity());
           responseSelling.setSubtotal(subtotal);
           responseSellingList.add(responseSelling);
       }

       if (total.compareTo(BigDecimal.ZERO)<=0){
           throw new RuntimeException();
       }

        Ordered ordered = new Ordered();
        ordered.setCustomer(customer);
        ordered.setTotal(total);

        Ordered ordered1 = orderRepository.save(ordered);

        ResponseOrderDto responseOrderDto = new ResponseOrderDto();
        responseOrderDto.setId(ordered1.getId());
        responseOrderDto.setOrderedDate(ordered1.getOrderDate());
        responseOrderDto.setUsername(customer.getUsername());
        responseOrderDto.setEmail(customer.getEmail());
        responseOrderDto.setTotal(total);
        responseOrderDto.setResponseSellings(responseSellingList);

        customer.setPurchaseAmount(customer.getPurchaseAmount()+1);
        customerRepository.save(customer);

        for (OrderProduct orderProduct:orderProducts){
            orderProduct.setOrdered(ordered);
            orderProductRepository.save(orderProduct);
        }
        return responseOrderDto;
    }


    @Transactional(readOnly = true)
    @Override
    public ResponseOrderDto getById(Long id) {
        if (id==null || id<=0){
            throw new IdWrongValueException("Order",id);
        }
        Ordered ordered = orderRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("Order",id)); 

        ResponseOrderDto responseOrderDto = new ResponseOrderDto();
        responseOrderDto.setId(ordered.getId());
        responseOrderDto.setOrderedDate(ordered.getOrderDate());
        responseOrderDto.setUsername(ordered.getCustomer().getUsername());
        responseOrderDto.setEmail(ordered.getCustomer().getEmail());
        responseOrderDto.setTotal(ordered.getTotal());


        List<ResponseSelling> responseSellingList = new ArrayList<>();
        ordered.getOrderProducts().stream().forEach(
                orderProduct -> {
                    Product product = productRepository.findById(orderProduct.getProduct().getId())
                            .orElseThrow(()-> new NotFoundException("Product",orderProduct.getProduct().getId()));
                    ResponseSelling responseSelling = new ResponseSelling();
                    responseSelling.setProduct(product.getName());
                    responseSelling.setPrice(product.getPrice());
                    responseSelling.setQuantity(orderProduct.getQuantity());
                    responseSelling.setSubtotal(orderProduct.getSubtotal());
                    responseSellingList.add(responseSelling);
                }
        );

        responseOrderDto.setResponseSellings(responseSellingList);
        
        
        return responseOrderDto;
    }

    @Transactional
    @Override
    public void cancelOrder(Long id) {
        //Buscar la factura
        Ordered ordered = orderRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("Order",id));
        //Ver si ya está cancelada
        if (ordered.isCanceled()){
            throw new OrderedAlreadyCanceledException("Order is already canceled");
        }
        //Si no está cancelada setearla
        ordered.setCanceled(true);
        orderRepository.save(ordered);
    }

    @Transactional
    @Override
    public void deleteOrder(Long id) {
        Ordered ordered = orderRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("Order",id));

        if (ordered.isDeleted()){
            throw new OrderedAlreadyDeletedException("Order already deleted");
        }

        ordered.setDeleted(true);
        orderRepository.save(ordered);
    }


}
