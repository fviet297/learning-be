package com.learningapp.service;

import com.learningapp.dto.request.StudyModuleRequest;
import com.learningapp.dto.response.StudyModuleResponse;
import com.learningapp.entity.StudyModule;
import com.learningapp.mapper.StudyModuleMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class StudyModuleMapperTest {

    private StudyModuleMapper studyModuleMapper;

    private StudyModuleRequest studyModuleRequest;
    private StudyModule studyModule;

    @BeforeEach
    void setUp() {
        // Lấy instance của StudyModuleMapper
        studyModuleMapper = Mappers.getMapper(StudyModuleMapper.class);

        // Dữ liệu mẫu
        studyModuleRequest = new StudyModuleRequest();
        studyModuleRequest.setName("Test Module");
        studyModuleRequest.setDescription("Test Description");

        studyModule = new StudyModule();
        studyModule.setId("1");
        studyModule.setName("Test Module");
        studyModule.setDescription("Test Description");
        studyModule.setCreatedAt(LocalDateTime.now());
        studyModule.setUpdatedAt(LocalDateTime.now());
    }

    @Test
    void toEntity_WhenRequestIsValid_ReturnsStudyModule() {
        // Act
        StudyModule result = studyModuleMapper.toEntity(studyModuleRequest);

        // Assert
        assertNotNull(result);
        assertNull(result.getId()); // id bị ignore
        assertEquals("Test Module", result.getName());
        assertEquals("Test Description", result.getDescription());
    }

    @Test
    void toEntity_WhenRequestIsNull_ReturnsNull() {
        // Act
        StudyModule result = studyModuleMapper.toEntity(null);

        // Assert
        assertNull(result);
    }

    @Test
    void toResponse_WhenStudyModuleIsValid_ReturnsStudyModuleResponse() {
        // Act
        StudyModuleResponse result = studyModuleMapper.toResponse(studyModule);

        // Assert
        assertNotNull(result);
        assertEquals("1", result.getId());
        assertEquals("Test Module", result.getName());
        assertEquals("Test Description", result.getDescription());
    }

    @Test
    void toResponse_WhenStudyModuleIsNull_ReturnsNull() {
        // Act
        StudyModuleResponse result = studyModuleMapper.toResponse(null);

        // Assert
        assertNull(result);
    }
}