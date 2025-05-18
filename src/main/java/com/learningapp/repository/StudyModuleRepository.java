package com.learningapp.repository;

import com.learningapp.dto.response.StudyModuleProjection;
import com.learningapp.entity.StudyModule;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudyModuleRepository extends JpaRepository<StudyModule, String> {
    Optional<StudyModule> findByIdAndIsDeleteFalse(String id);

    Optional<Page<StudyModuleProjection>> findAllStudyModuleEntitiesByIsDeleteFalse(Pageable pageable);
}