package com.comercio.demo.handlerException;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class HandlerException {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Map<String,Object>> notFoundException(NotFoundException e){
        Map<String,Object> error = new HashMap<>();
        error.put("error",e.getEntity()+" not found");
        error.put("time", LocalDateTime.now());
        error.put("message",e.getMessage());

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(RepeatedException.class)
    public ResponseEntity<Map<String,Object>> repeatedException(RepeatedException e){
        Map<String,Object> error = new HashMap<>();
        error.put("error",e.getEntity()+ " already exist");
        error.put("time",LocalDateTime.now());
        error.put("message",e.getMessage());

        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,Object>> methodArgumentNotValidException(MethodArgumentNotValidException e){
        Map<String,Object> map = new HashMap<>();
        e.getBindingResult().getAllErrors().stream().forEach(
                error -> {
                    map.put(((FieldError) error).getField(),error.getDefaultMessage());
                }
        );

        return new ResponseEntity<>(map,HttpStatus.CONFLICT);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Map<String,Object>> httpMessageNotReadableException(HttpMessageNotReadableException e){
        Map<String,Object> map = new HashMap<>();
        map.put("error","El valor no es está dentro de los valores permitidos");
        return new ResponseEntity<>(map,HttpStatus.CONFLICT);
    }

    @ExceptionHandler(IdWrongValueException.class)
    public ResponseEntity<Map<String,Object>> idWrongValueException(IdWrongValueException e){
        Map<String,Object> error = new HashMap<>();
        error.put("error",e.getEntity()+" not found");
        error.put("time", LocalDateTime.now());
        error.put("id",e.getEntityId());
        error.put("message",e.getMessage());

        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(DuplicateKeyException.class)
    public ResponseEntity<Map<String,Object>> duplicateKeyException(DuplicateKeyException e){
        Map<String,Object> error = new HashMap<>();
        String messageError = e.getMessage();
        if (messageError.contains("username")){
            error.put("username","El username ya existe");
        }

        if (messageError.contains("email")){
            error.put("email","El email ya existe");
        }

        if (messageError.contains("dni")){
            error.put("dni","El dni ya existe");
        }


        return new ResponseEntity<>(error,HttpStatus.CONFLICT);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Map<String,Object>> dataIntegrityViolationException(DataIntegrityViolationException e){
        Map<String,Object> error = new HashMap<>();
        String messageError = e.getMessage();
        if (messageError.contains("username")){
            error.put("username","El username ya existe");
        }

        if (messageError.contains("email")){
            error.put("email","El email ya existe");
        }

        return new ResponseEntity<>(error,HttpStatus.CONFLICT);
    }

    @ExceptionHandler(NotStockException.class)
    public ResponseEntity<Map<String,String>> notStockException(NotStockException e){
        Map<String,String> error = new HashMap<>();
        error.put("error",e.getMessage());
        return new ResponseEntity<>(error,HttpStatus.CONFLICT);
    }

    @ExceptionHandler(OrderedAlreadyCanceledException.class)
    public ResponseEntity<Map<String,String>> orderedAlreadyCanceledException(OrderedAlreadyCanceledException e){
        Map<String,String> error = new HashMap<>();
        error.put("error",e.getMessage());

        return new ResponseEntity<>(error,HttpStatus.CONFLICT);
    }

    @ExceptionHandler(OrderedAlreadyDeletedException.class)
    public ResponseEntity<Map<String,String>> orderedAlreadyDeletedException(OrderedAlreadyDeletedException e){
        Map<String,String> error = new HashMap<>();
        error.put("error",e.getMessage());

        return new ResponseEntity<>(error,HttpStatus.CONFLICT);
    }
}
