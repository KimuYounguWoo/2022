package com.example.recipe2022.exception;

public class OAuth2NotFoundException extends RuntimeException {
    public OAuth2NotFoundException(String message) {
        super(message);
    }
}