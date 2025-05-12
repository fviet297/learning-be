package com.learningapp.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.learningapp.dto.UserDTO;
import lombok.Getter;

@Getter
public class UserRequest extends UserDTO {

    @JsonProperty
    private String password;
} 