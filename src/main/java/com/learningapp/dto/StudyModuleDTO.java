package com.learningapp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class StudyModuleDTO {

    @JsonProperty
    private String description;

    @JsonProperty
    private String name;
}
