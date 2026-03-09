package com.learningapp.repository;

import com.learningapp.entity.Flashcard;
import com.learningapp.enums.FlashcardStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FlashcardRepository extends JpaRepository<Flashcard, String> {

    /**
     *
     * @param status status of flashcard
     * @param studyModuleId Id of StudyModule that flash card be long
     * @return List of {@return Flashcard}
     *
     * */
    @Query("SELECT f FROM Flashcard f WHERE f.status = :status AND f.isDelete = false AND f.studyModule.id = :studyModuleId")
    List<Flashcard> findByStatusAndStudyModuleId(@Param("status") FlashcardStatus status, @Param("studyModuleId") String studyModuleId);

    @Query("SELECT f FROM Flashcard f WHERE f.isDelete = false AND f.studyModule.id = :studyModuleId")
    List<Flashcard> findByStudyModuleId(@Param("studyModuleId") String studyModuleId);

    /**
     *
     * @param id
     * @return
     */
    Optional<Flashcard> findByIdAndIsDeleteFalse(String id);
}