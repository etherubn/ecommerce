package com.comercio.demo.handlerException;

public class NotStockException extends RuntimeException{
    public NotStockException(Integer stock,Integer quantity,String product) {
        super(String.format("El stock es de %d, y está pidiendo %d de %s",stock,quantity,product));
    }
}
