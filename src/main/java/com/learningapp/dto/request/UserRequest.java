package com.learningapp.dto.request;

import com.learningapp.dto.UserDTO;
import lombok.Getter;

@Getter
public class UserRequest extends UserDTO {
    private String password;
} 