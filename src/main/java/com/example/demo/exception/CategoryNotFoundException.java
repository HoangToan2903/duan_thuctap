package com.example.demo.exception;

public class CategoryNotFoundException extends Throwable{
    public CategoryNotFoundException(String message){
        super(message);
    }
}
