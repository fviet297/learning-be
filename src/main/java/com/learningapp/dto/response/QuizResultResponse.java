package com.learningapp.dto.response;

import com.learningapp.dto.UserDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class QuizResultResponse extends UserDTO {

    private String id;
}