package com.learningapp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

/**
 * Data Transfer Object (DTO) representing error details in API responses.
 * This class is used to structure error information when returning error responses
 * to API clients.
 */
@Builder
@Getter
public class ErrorDetail {

    /**
     * The error code that identifies the type of error
     */
    @JsonProperty
    private String errorCode;

    /**
     * A human-readable message describing the error
     */
    @JsonProperty
    private String message;
}
