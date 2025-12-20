package com.example.demo.exception;

/**
 * Runtime exception used for validation and business rule failures.
 * IMPORTANT:
 * - Exception message MUST contain specific keywords
 * - Tests validate using ex.getMessage().contains("keyword")
 */
public class BadRequestException extends RuntimeException {

    public BadRequestException(String message) {
        super(message);
    }
}
