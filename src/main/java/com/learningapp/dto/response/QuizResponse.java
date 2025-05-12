package com.learningapp.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.learningapp.dto.UserDTO;
import lombok.*;

@Getter
@Setter
@Builder
public class QuizResponse extends UserDTO {

    private String id;
}