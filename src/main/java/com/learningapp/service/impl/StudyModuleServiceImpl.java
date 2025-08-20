package com.learningapp.service.impl;

import com.learningapp.common.CommonUtils;
import com.learningapp.constants.Constants;
import com.learningapp.dto.ResponseData;
import com.learningapp.dto.request.StudyModuleRequest;
import com.learningapp.dto.response.StudyModuleProjection;
import com.learningapp.dto.response.StudyModuleResponse;
import com.learningapp.entity.StudyModule;
import com.learningapp.exception.NotFoundException;
import com.learningapp.mapper.StudyModuleMapper;
import com.learningapp.repository.StudyModuleRepository;
import com.learningapp.service.GoogleSheetsService;
import com.learningapp.service.StudyModuleService;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

/**
 * Implementation of the StudyModuleService interface.
 * This class provides the business logic for managing study modules.
 */
@Service
public class StudyModuleServiceImpl implements StudyModuleService {

    private final StudyModuleRepository studyModuleRepository;
    private final StudyModuleMapper studyModuleMapper;
    private final GoogleSheetsService googleSheetsService;

    /**
     * Constructs a new StudyModuleServiceImpl with required dependencies.
     *
     * @param studyModuleRepository the repository for study module data access
     * @param studyModuleMapper     the mapper for converting between DTOs and entities
     */
    @Autowired
    public StudyModuleServiceImpl(final GoogleSheetsService googleSheetsService,final StudyModuleRepository studyModuleRepository, final StudyModuleMapper studyModuleMapper) {
        this.studyModuleRepository = studyModuleRepository;
        this.studyModuleMapper = studyModuleMapper;
        this.googleSheetsService=googleSheetsService;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public StudyModuleResponse create(@NotNull final StudyModuleRequest studyModuleRequest) {
        StudyModule studyModule = studyModuleMapper.toEntity(studyModuleRequest);
        studyModule.setUserId(CommonUtils.getCurrentUserId());
        studyModule = studyModuleRepository.save(studyModule);
        return studyModuleMapper.toResponse(studyModule);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public StudyModuleResponse update(@NotBlank final String id, @NotNull final StudyModuleRequest studyModuleRequest) {
        StudyModule studyModule = getEntityById(id);
        studyModule.setName(studyModuleRequest.getName());
        studyModule.setDescription(studyModuleRequest.getDescription());
        return studyModuleMapper.toResponse(studyModule);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public StudyModuleResponse getById(@NotBlank final String id) {
        final StudyModule studyModule = this.getEntityById(id);
        return studyModuleMapper.toResponse(studyModule);
    }

    @Transactional
    @Override
    public ResponseData deleteModule(final String id) {
        final StudyModule studyModule = getEntityById(id);
        studyModule.setIsDelete(true);
        studyModule.getFlashcards().forEach(f -> {
            f.setIsDelete(true);
        });
        studyModule.getQuizzes().forEach(q -> {
            q.setIsDelete(true);
        });
        return ResponseData.builder().build();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public StudyModule getEntityById(@NotBlank final String id) {
        return studyModuleRepository
                .findByIdAndIsDeleteFalse(id)
                .orElseThrow(
                        () -> new NotFoundException(
                                String.format(
                                        Constants.MESSAGE_ERROR.NOT_FOUND_ENTITY,
                                        StudyModule.class.getSimpleName(),
                                        id
                                )));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Page<StudyModuleProjection> getPageStudyModules(final Pageable pageable) {
        final String userId = CommonUtils.getCurrentUserId();
        return studyModuleRepository
                .findAllStudyModuleEntitiesByUserIdAndIsDeleteFalse(userId, pageable)
                .orElseThrow(() -> new NotFoundException(String.format(
                        Constants.MESSAGE_ERROR.NO_DATA,
                        StudyModule.class.getSimpleName()
                )));
    }

    @Override
    public List<StudyModule> getAllGGSheet() throws IOException {
        return googleSheetsService.getAllStudyModules();
    }
}
