package com.learningapp.repository;

import com.learningapp.dto.response.StudyModuleProjection;
import com.learningapp.entity.StudyModule;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Repository interface for managing StudyModule entities.
 * This interface extends JpaRepository to provide basic CRUD operations
 * and adds custom query methods for study module-specific operations.
 */
public interface StudyModuleRepository extends JpaRepository<StudyModule, String> {
    /**
     * Finds a study module by its ID that is not deleted.
     *
     * @param id the unique identifier of the study module
     * @return an Optional containing the study module if found and not deleted
     */
    Optional<StudyModule> findByIdAndIsDeleteFalse(String id);

    /**
     * Finds all study modules that are not deleted, with pagination support.
     *
     * @param pageable the pagination information
     * @return an Optional containing a page of study module projections
     */
    Optional<Page<StudyModuleProjection>> findAllStudyModuleEntitiesByIsDeleteFalse(Pageable pageable);
}