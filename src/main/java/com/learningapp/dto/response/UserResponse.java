package com.learningapp.dto.response;

import com.learningapp.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Builder
@AllArgsConstructor
public class UserResponse extends UserDTO {
    private String id;
}