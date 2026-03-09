package com.learningapp.dto.request;

import com.learningapp.dto.UserDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
@NoArgsConstructor
public class RegisterRequest extends UserDTO {
    private String password;
}
