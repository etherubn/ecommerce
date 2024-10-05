package com.comercio.demo.handlerException;

import lombok.Getter;

@Getter
public class NotFoundException extends RuntimeException{
    private String entity;
    private Long entityId;

    public NotFoundException(String entity,Long entityId) {
        super(String.format("%s con el id %d no se encuentra",entity,entityId));
        this.entity = entity;
        this.entityId = entityId;
    }
}
