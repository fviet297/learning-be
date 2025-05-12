package com.learningapp.repository;

import com.learningapp.entity.StudyModule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudyModuleRepository extends JpaRepository<StudyModule, Long> {
} 