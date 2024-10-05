package com.comercio.demo.handlerException;

public class OrderedAlreadyDeletedException extends RuntimeException{
    public OrderedAlreadyDeletedException(String message) {
        super(message);
    }
}
