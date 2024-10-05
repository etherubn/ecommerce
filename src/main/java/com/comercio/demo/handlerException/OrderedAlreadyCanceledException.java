package com.comercio.demo.handlerException;

public class OrderedAlreadyCanceledException extends RuntimeException{
    public OrderedAlreadyCanceledException(String message) {
        super(message);
    }
}
