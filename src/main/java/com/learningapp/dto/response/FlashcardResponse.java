package com.learningapp.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.learningapp.dto.UserDTO;
import lombok.*;

@Getter
@Setter
@Builder
public class FlashcardResponse extends UserDTO {

    private String id;
}