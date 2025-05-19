package com.learningapp.service;

import com.learningapp.constants.CoreConstants;
import com.learningapp.dto.request.FlashcardRequest;
import com.learningapp.dto.response.FlashcardResponse;
import com.learningapp.entity.Flashcard;
import com.learningapp.entity.StudyModule;
import com.learningapp.enums.FlashcardStatus;
import com.learningapp.exception.NotFoundException;
import com.learningapp.mapper.FlashcardMapper;
import com.learningapp.repository.FlashcardRepository;
import com.learningapp.service.impl.FlashcardServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FlashcardServiceTest {

    @Mock
    private FlashcardRepository flashcardRepository;

    @Mock
    private FlashcardMapper flashcardMapper;

    @Mock
    private StudyModuleService studyModuleService;

    @InjectMocks
    private FlashcardServiceImpl flashcardService;

    private Flashcard flashcard;
    private FlashcardRequest flashcardRequest;
    private FlashcardResponse flashcardResponse;
    private StudyModule studyModule;

    @BeforeEach
    void setUp() {
        // Khởi tạo dữ liệu mẫu
        studyModule = new StudyModule();
        studyModule.setId("1");
        studyModule.setName("Test Module");

        flashcard = new Flashcard();
        flashcard.setId("fc1");
        flashcard.setQuestion("Front Text");
        flashcard.setAnswer("Back Text");
        flashcard.setStudyModule(studyModule);
        flashcard.setStatus(FlashcardStatus.LEARN);

        flashcardRequest = new FlashcardRequest();
        flashcardRequest.setQuestion("Front Text");
        flashcardRequest.setAnswer("Back Text");
        flashcardRequest.setStudyModuleId("1");

        flashcardResponse = new FlashcardResponse();
        flashcardResponse.setId("fc1");
        flashcardResponse.setQuestion("Front Text");
        flashcardResponse.setAnswer("Back Text");
        flashcardResponse.setStatus(FlashcardStatus.LEARN);
    }

    @Test
    void create_WhenRequestIsValid_ReturnsFlashcardResponse() {
        // Arrange
        when(flashcardMapper.toEntity(any(FlashcardRequest.class))).thenReturn(flashcard);
        when(studyModuleService.getEntityById(anyString())).thenReturn(studyModule);
        when(flashcardRepository.save(any(Flashcard.class))).thenReturn(flashcard);
        when(flashcardMapper.toResponse(any(Flashcard.class))).thenReturn(flashcardResponse);

        // Act
        FlashcardResponse result = flashcardService.create(flashcardRequest);

        // Assert
        assertNotNull(result);
        assertEquals("fc1", result.getId());
        assertEquals("Front Text", result.getQuestion());
        assertEquals("Back Text", result.getAnswer());
        assertEquals(FlashcardStatus.LEARN, result.getStatus());

        // Verify
        verify(flashcardMapper, times(1)).toEntity(flashcardRequest);
        verify(studyModuleService, times(1)).getEntityById("1");
        verify(flashcardRepository, times(1)).save(flashcard);
        verify(flashcardMapper, times(1)).toResponse(flashcard);
    }

    @Test
    void create_WhenStudyModuleNotFound_ThrowsNotFoundException() {
        // Arrange
        when(flashcardMapper.toEntity(any(FlashcardRequest.class))).thenReturn(flashcard);
        when(studyModuleService.getEntityById(anyString())).thenThrow(new NotFoundException("StudyModule not found"));

        // Act & Assert
        NotFoundException exception = assertThrows(NotFoundException.class, () -> flashcardService.create(flashcardRequest));
        assertTrue(exception.getMessage().contains("StudyModule not found"));

        // Verify
        verify(flashcardMapper, times(1)).toEntity(flashcardRequest);
        verify(studyModuleService, times(1)).getEntityById("1");
        verify(flashcardRepository, never()).save(any());
        verify(flashcardMapper, never()).toResponse(any());
    }

    @Test
    void random_WhenFlashcardsExist_ReturnsFlashcardResponse() {
        // Arrange
        List<Flashcard> flashcards = Collections.singletonList(flashcard);
        when(flashcardRepository.findByStatusAndStudyModuleId(FlashcardStatus.LEARN, "1")).thenReturn(flashcards);
        when(flashcardMapper.toResponse(any(Flashcard.class))).thenReturn(flashcardResponse);

        // Act
        FlashcardResponse result = flashcardService.random("1");

        // Assert
        assertNotNull(result);
        assertEquals("fc1", result.getId());
        assertEquals("Front Text", result.getQuestion());
        assertEquals("Back Text", result.getAnswer());
        assertEquals(FlashcardStatus.LEARN, result.getStatus());

        // Verify
        verify(flashcardRepository, times(1)).findByStatusAndStudyModuleId(FlashcardStatus.LEARN, "1");
        verify(flashcardMapper, times(1)).toResponse(flashcard);
    }

    @Test
    void random_WhenNoFlashcards_ThrowsNotFoundException() {
        // Arrange
        when(flashcardRepository.findByStatusAndStudyModuleId(FlashcardStatus.LEARN, "1")).thenReturn(Collections.emptyList());

        // Act & Assert
        assertNull(flashcardService.random("1"));

        // Verify
        verify(flashcardRepository, times(1)).findByStatusAndStudyModuleId(FlashcardStatus.LEARN, "1");
        verify(flashcardMapper, never()).toResponse(any());
    }

    @Test
    void updateFlashcardStatus_WhenIdExists_ReturnsUpdatedFlashcardResponse() {
        // Arrange
        FlashcardStatus newStatus = FlashcardStatus.LEARN;
        when(flashcardRepository.findByIdAndIsDeleteFalse("fc1")).thenReturn(Optional.of(flashcard));
        when(flashcardMapper.toResponse(any(Flashcard.class))).thenReturn(flashcardResponse);

        // Act
        FlashcardResponse result = flashcardService.updateFlashcardStatus("fc1", newStatus);

        // Assert
        assertNotNull(result);
        assertEquals("fc1", result.getId());
        assertEquals(newStatus, result.getStatus());

        // Verify
        verify(flashcardRepository, times(1)).findByIdAndIsDeleteFalse("fc1");
        verify(flashcardMapper, times(1)).toResponse(flashcard);
    }

    @Test
    void updateFlashcardStatus_WhenIdNotExists_ThrowsNotFoundException() {
        // Arrange
        when(flashcardRepository.findByIdAndIsDeleteFalse("fc2")).thenReturn(Optional.empty());

        // Act & Assert
        NotFoundException exception = assertThrows(NotFoundException.class, () -> flashcardService.updateFlashcardStatus("fc2", FlashcardStatus.KNOW));
        assertEquals(String.format(CoreConstants.MESSAGE_ERROR.NOT_FOUND_ENTITY, StudyModule.class.getSimpleName(), "fc2"), exception.getMessage());

        // Verify
        verify(flashcardRepository, times(1)).findByIdAndIsDeleteFalse("fc2");
        verify(flashcardMapper, never()).toResponse(any());
    }

    @Test
    void getEntityById_WhenIdExists_ReturnsFlashcard() {
        // Arrange
        when(flashcardRepository.findByIdAndIsDeleteFalse("fc1")).thenReturn(Optional.of(flashcard));

        // Act
        Flashcard result = flashcardService.getEntityById("fc1");

        // Assert
        assertNotNull(result);
        assertEquals("fc1", result.getId());
        assertEquals("Front Text", result.getQuestion());
        assertEquals("Back Text", result.getAnswer());
        assertEquals(FlashcardStatus.LEARN, result.getStatus());

        // Verify
        verify(flashcardRepository, times(1)).findByIdAndIsDeleteFalse("fc1");
    }

    @Test
    void getEntityById_WhenIdNotExists_ThrowsNotFoundException() {
        // Arrange
        when(flashcardRepository.findByIdAndIsDeleteFalse("fc2")).thenReturn(Optional.empty());

        // Act & Assert
        NotFoundException exception = assertThrows(NotFoundException.class, () -> flashcardService.getEntityById("fc2"));
        assertEquals(String.format(CoreConstants.MESSAGE_ERROR.NOT_FOUND_ENTITY, StudyModule.class.getSimpleName(), "fc2"), exception.getMessage());

        // Verify
        verify(flashcardRepository, times(1)).findByIdAndIsDeleteFalse("fc2");
    }
}