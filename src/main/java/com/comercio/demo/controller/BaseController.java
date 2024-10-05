package com.comercio.demo.controller;

import com.comercio.demo.dto.response.ResponseMessageDto;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface BaseController<T,U> {

    ResponseEntity<List<U>> getAll();
    ResponseEntity<U> create(T t);
    ResponseEntity<U> update(Long id,T t);
    ResponseEntity<Void> delete(Long id);
    ResponseEntity<U> getById(Long id);
}
