package com.learningapp.service;

import com.learningapp.constants.CoreConstants;
import com.learningapp.dto.request.StudyModuleRequest;
import com.learningapp.dto.response.StudyModuleProjection;
import com.learningapp.dto.response.StudyModuleResponse;
import com.learningapp.entity.StudyModule;
import com.learningapp.exception.NotFoundException;
import com.learningapp.mapper.StudyModuleMapper;
import com.learningapp.repository.StudyModuleRepository;
import com.learningapp.service.impl.StudyModuleServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StudyModuleServiceTest {

    @Mock
    private StudyModuleRepository studyModuleRepository;

    @Mock
    private StudyModuleMapper studyModuleMapper;

    @InjectMocks
    private StudyModuleServiceImpl studyModuleService;

    private StudyModule studyModule;
    private StudyModuleRequest studyModuleRequest;
    private StudyModuleResponse studyModuleResponse;
    private StudyModuleProjection studyModuleProjection;
    private Pageable pageable;

    @BeforeEach
    void setUp() {
        // Khởi tạo dữ liệu mẫu
        studyModule = new StudyModule();
        studyModule.setId("1");
        studyModule.setName("Test Module");
        studyModule.setDescription("Test Description");

        studyModuleRequest = new StudyModuleRequest();
        studyModuleRequest.setName("Test Module");
        studyModuleRequest.setDescription("Test Description");

        studyModuleResponse = new StudyModuleResponse();
        studyModuleResponse.setId("1");
        studyModuleResponse.setName("Test Module");
        studyModuleResponse.setDescription("Test Description");

        studyModuleProjection = new StudyModuleProjection() {
            @Override
            public String getId() { return "1"; }
            @Override
            public String getName() { return "Test Module"; }

            @Override
            public LocalDateTime getUpdatedAt() {
                return LocalDateTime.now();
            }

            @Override
            public String getDescription() { return "Test Description"; }
        };

        pageable = PageRequest.of(0, 10);
    }

    @Test
    void create_WhenRequestIsValid_ReturnsStudyModuleResponse() {
        // Arrange
        when(studyModuleMapper.toEntity(any(StudyModuleRequest.class))).thenReturn(studyModule);
        when(studyModuleRepository.save(any(StudyModule.class))).thenReturn(studyModule);
        when(studyModuleMapper.toResponse(any(StudyModule.class))).thenReturn(studyModuleResponse);

        // Act
        StudyModuleResponse result = studyModuleService.create(studyModuleRequest);

        // Assert
        assertNotNull(result);
        assertEquals("1", result.getId());
        assertEquals("Test Module", result.getName());
        verify(studyModuleMapper, times(1)).toEntity(studyModuleRequest);
        verify(studyModuleRepository, times(1)).save(studyModule);
        verify(studyModuleMapper, times(1)).toResponse(studyModule);
    }

    @Test
    void getById_WhenIdExists_ReturnsStudyModuleResponse() {
        // Arrange
        when(studyModuleRepository.findByIdAndIsDeleteFalse("1")).thenReturn(Optional.of(studyModule));
        when(studyModuleMapper.toResponse(any(StudyModule.class))).thenReturn(studyModuleResponse);

        // Act
        StudyModuleResponse result = studyModuleService.getById("1");

        // Assert
        assertNotNull(result);
        assertEquals("1", result.getId());
        verify(studyModuleRepository, times(1)).findByIdAndIsDeleteFalse("1");
        verify(studyModuleMapper, times(1)).toResponse(studyModule);
    }

    @Test
    void getById_WhenIdNotExists_ThrowsNotFoundException() {
        // Arrange
        when(studyModuleRepository.findByIdAndIsDeleteFalse("2")).thenReturn(Optional.empty());

        // Act & Assert
        NotFoundException exception = assertThrows(NotFoundException.class, () -> studyModuleService.getById("2"));
        assertEquals(String.format(CoreConstants.MESSAGE_ERROR.NOT_FOUND_ENTITY, StudyModule.class.getSimpleName(), "2"), exception.getMessage());
        verify(studyModuleRepository, times(1)).findByIdAndIsDeleteFalse("2");
        verify(studyModuleMapper, never()).toResponse(any());
    }

    @Test
    void getEntityById_WhenIdExists_ReturnsStudyModule() {
        // Arrange
        when(studyModuleRepository.findByIdAndIsDeleteFalse("1")).thenReturn(Optional.of(studyModule));

        // Act
        StudyModule result = studyModuleService.getEntityById("1");

        // Assert
        assertNotNull(result);
        assertEquals("1", result.getId());
        verify(studyModuleRepository, times(1)).findByIdAndIsDeleteFalse("1");
    }

    @Test
    void getEntityById_WhenIdNotExists_ThrowsNotFoundException() {
        // Arrange
        when(studyModuleRepository.findByIdAndIsDeleteFalse("2")).thenReturn(Optional.empty());

        // Act & Assert
        NotFoundException exception = assertThrows(NotFoundException.class, () -> studyModuleService.getEntityById("2"));
        assertEquals(String.format(CoreConstants.MESSAGE_ERROR.NOT_FOUND_ENTITY, StudyModule.class.getSimpleName(), "2"), exception.getMessage());
        verify(studyModuleRepository, times(1)).findByIdAndIsDeleteFalse("2");
    }

    @Test
    void getPageStudyModules_WhenDataExists_ReturnsPage() {
        // Arrange
        Page<StudyModuleProjection> page = new PageImpl<>(Arrays.asList(studyModuleProjection));
        when(studyModuleRepository.findAllStudyModuleEntitiesByIsDeleteFalse(pageable)).thenReturn(Optional.of(page));

        // Act
        Page<StudyModuleProjection> result = studyModuleService.getPageStudyModules(pageable);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.getContent().size());
        assertEquals("1", result.getContent().get(0).getId());
        verify(studyModuleRepository, times(1)).findAllStudyModuleEntitiesByIsDeleteFalse(pageable);
    }

    @Test
    void getPageStudyModules_WhenNoData_ThrowsNotFoundException() {
        // Arrange
        when(studyModuleRepository.findAllStudyModuleEntitiesByIsDeleteFalse(pageable)).thenReturn(Optional.empty());

        // Act & Assert
        NotFoundException exception = assertThrows(NotFoundException.class, () -> studyModuleService.getPageStudyModules(pageable));
        assertEquals(String.format(CoreConstants.MESSAGE_ERROR.NO_DATA, StudyModule.class.getSimpleName()), exception.getMessage());
        verify(studyModuleRepository, times(1)).findAllStudyModuleEntitiesByIsDeleteFalse(pageable);
    }
}