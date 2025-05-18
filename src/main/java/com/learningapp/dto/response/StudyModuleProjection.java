package com.learningapp.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDateTime;

public interface StudyModuleProjection {
    String getId();

    String getDescription();

    String getName();

    @JsonIgnore
    LocalDateTime getUpdatedAt();
}
