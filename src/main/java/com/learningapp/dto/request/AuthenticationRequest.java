package com.learningapp.dto.request;

import com.learningapp.dto.UserDTO;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.validator.constraints.Length;

@SuperBuilder
@Getter
@NoArgsConstructor
public class AuthenticationRequest extends UserDTO {

    @NotBlank
    @Length(min= 6)
    private String password;
}
