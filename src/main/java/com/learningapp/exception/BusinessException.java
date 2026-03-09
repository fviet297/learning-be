package com.learningapp.exception;

/**
 * Exception thrown when a business rule or constraint is violated.
 * This exception is used to handle business logic errors that occur during
 * the normal operation of the application.
 */
public class BusinessException extends RuntimeException {

    /**
     * Constructs a new BusinessException with the specified error message.
     *
     * @param message the detail message explaining the business rule violation
     */
    public BusinessException(String message) {
        super(message);
    }
}
