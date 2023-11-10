package com.example.sectors.exception;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String resourceName, Object fieldValue) {
        super(String.format("%s with value [%s] not found", resourceName, fieldValue));
    }
}
