package com.comercio.demo.controller;

import org.springframework.http.ResponseEntity;

import java.util.List;

public interface OrderBaseController<T,U> {

    ResponseEntity<List<U>> getAll();
    ResponseEntity<U> create(T t);
    ResponseEntity<U> getById(Long id);
    ResponseEntity<Void> deleteOrder(Long id);
    ResponseEntity<Void> cancelOrder(Long id);

}
