package com.learningapp.service;

import com.learningapp.dto.request.StudyModuleRequest;
import com.learningapp.dto.response.StudyModuleProjection;
import com.learningapp.dto.response.StudyModuleResponse;
import com.learningapp.entity.StudyModule;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface StudyModuleService {
    StudyModuleResponse create(StudyModuleRequest studyModuleRequest);

    StudyModuleResponse getById(String id);

    StudyModule getEntityById(String id);

    Page<StudyModuleProjection> getPageStudyModules(final Pageable pageable);
}
