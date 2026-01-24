package com.learningapp.service;

import com.learningapp.dto.request.StudyModuleRequest;
import com.learningapp.dto.response.StudyModuleResponse;

public interface GenerationService {
    StudyModuleResponse generateAndSave(StudyModuleRequest studyModuleRequest);
}
