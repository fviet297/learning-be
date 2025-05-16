package com.learningapp.service;

import com.learningapp.dto.request.StudyModuleRequest;
import com.learningapp.dto.response.StudyModuleResponse;
import com.learningapp.entity.StudyModuleEntity;

public interface StudyModuleService {
    StudyModuleResponse create(StudyModuleRequest studyModuleRequest);

    StudyModuleResponse getById(String id);

    StudyModuleEntity findById(String id);
}
