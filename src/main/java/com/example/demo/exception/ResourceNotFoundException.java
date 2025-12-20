package com.example.demo.exception;

/**
 * Runtime exception used when a requested resource is not found.
 * IMPORTANT:
 * - Message MUST contain the words "not found"
 */
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
