package com.learningapp.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class StudyModuleDTO implements Serializable {

    private String id;

    private String description;

    @NotBlank
    private String name;
}
