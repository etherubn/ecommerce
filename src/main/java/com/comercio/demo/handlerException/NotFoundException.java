package com.comercio.demo.handlerException;

import lombok.Getter;

@Getter
public class NotFoundException extends RuntimeException{
    private String entity;

    public NotFoundException(String entity) {
        super(String.format("%s no se encuentra en BD",entity));
        this.entity = entity;
    }

}
