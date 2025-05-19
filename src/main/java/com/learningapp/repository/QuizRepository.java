package com.learningapp.repository;

import com.learningapp.entity.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, String> {
    /**
     * @param studyModuleId Id of StudyModule that quiz be long
     * @return List of {@return Quiz}
     */
    @Query("SELECT q FROM Quiz q WHERE q.isDelete = false AND q.studyModule.id = :studyModuleId")
    List<Quiz> findByStudyModuleId(@Param("studyModuleId") String studyModuleId);

    Optional<Quiz> findByIdAndIsDeleteIsFalse(String id);
}