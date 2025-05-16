package com.learningapp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ErrorDetail {

    @JsonProperty
    private String errorCode;

    @JsonProperty
    private String message;
}
