package com.comercio.demo.controller;

import com.comercio.demo.dto.request.CreateOrderDto;
import com.comercio.demo.dto.request.CreateReviewDto;
import com.comercio.demo.dto.response.ResponseOrderDto;
import com.comercio.demo.dto.response.ResponseReviewDto;
import com.comercio.demo.service.OrderService;
import com.comercio.demo.service.ReviewService;
import com.comercio.demo.service.impl.OrderServiceImpl;
import com.comercio.demo.service.impl.ReviewServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController implements OrderBaseController<CreateOrderDto,ResponseOrderDto>{

    private final OrderService orderService;

    public OrderController(OrderServiceImpl orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    @Override
    public ResponseEntity<List<ResponseOrderDto>> getAll() {
        return new ResponseEntity<>(orderService.findAll(),HttpStatus.OK);
    }

    @PostMapping
    @Override
    public ResponseEntity<ResponseOrderDto> create(@Valid @RequestBody CreateOrderDto createOrderDto) {
        return new ResponseEntity<>(orderService.create(createOrderDto),HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @Override
    public ResponseEntity<ResponseOrderDto> getById(@PathVariable Long id) {
        return new ResponseEntity<>(orderService.getById(id),HttpStatus.OK);
    }

    @PatchMapping("/delete/{id}")
    @Override
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @PatchMapping("/cancel/{id}")
    @Override
    public ResponseEntity<Void> cancelOrder(@PathVariable Long id) {
        orderService.cancelOrder(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
