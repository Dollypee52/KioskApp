package com.semicolon.kioskApp.exception;

public class ProductDoesNotExistException extends KioskException{

    public ProductDoesNotExistException(String message){
        super(message);
    }
}
