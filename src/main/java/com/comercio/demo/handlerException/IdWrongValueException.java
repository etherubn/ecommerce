package com.comercio.demo.handlerException;

import lombok.Getter;

@Getter
public class IdWrongValueException extends RuntimeException {
    private String entity;
    private Long entityId;

    public IdWrongValueException(String entity, Long entityId) {
        super(String.format("Para la entidad %s, el id %d es un valor erróneo",entity,entityId));
        this.entity = entity;
        this.entityId = entityId;
    }
}
