package com.learningapp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class UserDTO {

    @JsonProperty
    private String username;

    @JsonProperty
    private String email;

    @JsonProperty
    private String fullName;
} 