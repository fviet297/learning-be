package com.learningapp.service;

import com.learningapp.dto.request.StudyModuleRequest;
import com.learningapp.dto.response.StudyModuleProjection;
import com.learningapp.dto.response.StudyModuleResponse;
import com.learningapp.entity.StudyModule;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service interface for managing study modules in the learning application.
 * This interface defines the contract for study module-related operations.
 */
public interface StudyModuleService {
    /**
     * Creates a new study module.
     *
     * @param studyModuleRequest the request containing study module details
     * @return the created study module response
     */
    StudyModuleResponse create(StudyModuleRequest studyModuleRequest);

    /**
     * Retrieves a study module by its ID.
     *
     * @param id the unique identifier of the study module
     * @return the study module response
     */
    StudyModuleResponse getById(String id);

    /**
     * Retrieves a study module entity by its ID.
     *
     * @param id the unique identifier of the study module
     * @return the study module entity
     */
    StudyModule getEntityById(String id);

    /**
     * Retrieves a paginated list of study modules.
     *
     * @param pageable the pagination information
     * @return a page of study module projections
     */
    Page<StudyModuleProjection> getPageStudyModules(final Pageable pageable);
}
