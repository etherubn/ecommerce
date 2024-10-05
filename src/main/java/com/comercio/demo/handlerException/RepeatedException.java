package com.comercio.demo.handlerException;

import lombok.Getter;

@Getter
public class RepeatedException extends RuntimeException{
    private String entity;
    private Long entityId;

    public RepeatedException(String entity) {
        super(String.format("%s ya existe",entity));
        this.entity=entity;
        this.entityId=entityId;
    }
}
