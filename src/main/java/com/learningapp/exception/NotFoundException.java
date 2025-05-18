package com.learningapp.exception;

/**
 * Exception thrown when a requested resource is not found in the system.
 * This exception is typically used when attempting to access or manipulate
 * an entity that does not exist in the database.
 */
public class NotFoundException extends RuntimeException {
    /**
     * Constructs a new NotFoundException with the specified error message.
     *
     * @param message the detail message explaining the reason for the exception
     */
    public NotFoundException(String message) {
        super(message);
    }
}