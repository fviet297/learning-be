package com.learningapp.mapper;

import com.learningapp.dto.request.FlashcardRequest;
import com.learningapp.dto.response.FlashcardResponse;
import com.learningapp.entity.Flashcard;
import com.learningapp.entity.StudyModule;
import com.learningapp.enums.FlashcardStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.*;

class FlashcardMapperTest {

    private FlashcardMapper flashcardMapper;

    private FlashcardRequest flashcardRequest;
    private Flashcard flashcard;
    private FlashcardResponse flashcardResponse;

    @BeforeEach
    void setUp() {
        // Lấy instance của FlashcardMapper
        flashcardMapper = Mappers.getMapper(FlashcardMapper.class);

        // Dữ liệu mẫu
        StudyModule studyModule = new StudyModule();
        studyModule.setId("1");

        flashcardRequest = new FlashcardRequest();
        flashcardRequest.setQuestion("Front Text");
        flashcardRequest.setAnswer("Back Text");
        flashcardRequest.setStudyModuleId("1");
        flashcardRequest.setStatus(FlashcardStatus.LEARN);

        flashcard = new Flashcard();
        flashcard.setId("fc1");
        flashcard.setQuestion("Front Text");
        flashcard.setAnswer("Back Text");
        flashcard.setStudyModule(studyModule);
        flashcard.setStatus(FlashcardStatus.LEARN);

        flashcardResponse = new FlashcardResponse();
        flashcardResponse.setId("fc1");
        flashcardResponse.setQuestion("Front Text");
        flashcardResponse.setAnswer("Back Text");
        flashcardResponse.setStatus(FlashcardStatus.LEARN);
    }

    @Test
    void toEntity_WhenRequestIsValid_ReturnsFlashcard() {
        // Act
        Flashcard result = flashcardMapper.toEntity(flashcardRequest);

        // Assert
        assertNotNull(result);
        assertNull(result.getId());
        assertNull(result.getStudyModule());
        assertEquals("Front Text", result.getQuestion());
        assertEquals("Back Text", result.getAnswer());
        assertEquals(FlashcardStatus.LEARN, result.getStatus());
    }

    @Test
    void toEntity_WhenRequestIsNull_ReturnsNull() {
        // Act
        Flashcard result = flashcardMapper.toEntity(null);

        // Assert
        assertNull(result);
    }

    @Test
    void toResponse_WhenFlashcardIsValid_ReturnsFlashcardResponse() {
        // Act
        FlashcardResponse result = flashcardMapper.toResponse(flashcard);

        // Assert
        assertNotNull(result);
        assertEquals("fc1", result.getId());
        assertEquals("Front Text", result.getQuestion());
        assertEquals("Back Text", result.getAnswer());
        assertEquals(FlashcardStatus.LEARN, result.getStatus());
    }

    @Test
    void toResponse_WhenFlashcardIsNull_ReturnsNull() {
        // Act
        FlashcardResponse result = flashcardMapper.toResponse(null);

        // Assert
        assertNull(result);
    }
}