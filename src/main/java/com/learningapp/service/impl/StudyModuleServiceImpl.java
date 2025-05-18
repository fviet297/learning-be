package com.learningapp.service.impl;

import com.learningapp.constants.CoreConstants;
import com.learningapp.dto.request.StudyModuleRequest;
import com.learningapp.dto.response.StudyModuleProjection;
import com.learningapp.dto.response.StudyModuleResponse;
import com.learningapp.entity.StudyModule;
import com.learningapp.exception.NotFoundException;
import com.learningapp.mapper.StudyModuleMapper;
import com.learningapp.repository.StudyModuleRepository;
import com.learningapp.service.StudyModuleService;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class StudyModuleServiceImpl implements StudyModuleService {

    private final StudyModuleRepository studyModuleRepository;
    private final StudyModuleMapper studyModuleMapper;

    @Autowired
    public StudyModuleServiceImpl(final StudyModuleRepository studyModuleRepository, final StudyModuleMapper studyModuleMapper) {
        this.studyModuleRepository = studyModuleRepository;
        this.studyModuleMapper = studyModuleMapper;
    }

    @Override
    public StudyModuleResponse create(@NotNull final StudyModuleRequest studyModuleRequest) {

        StudyModule studyModule = studyModuleMapper.toEntity(studyModuleRequest);
        studyModule = studyModuleRepository.save(studyModule);
        return studyModuleMapper.toResponse(studyModule);
    }

    @Override
    public StudyModuleResponse getById(@NotBlank final String id) {
        final StudyModule studyModule = this.getEntityById(id);
        return studyModuleMapper.toResponse(studyModule);
    }

    @Override
    public StudyModule getEntityById(@NotBlank final String id) {
        return studyModuleRepository
                .findByIdAndIsDeleteFalse(id)
                .orElseThrow(
                        () -> new NotFoundException(
                                String.format(
                                        CoreConstants.MESSAGE_ERROR.NOT_FOUND_ENTITY,
                                        StudyModule.class.getSimpleName(),
                                        id
                                )));
    }

    @Override
    public Page<StudyModuleProjection> getPageStudyModules(final Pageable pageable) {
        return studyModuleRepository
                .findAllStudyModuleEntitiesByIsDeleteFalse(pageable)
                .orElseThrow(() -> new NotFoundException(String.format(
                        CoreConstants.MESSAGE_ERROR.NO_DATA,
                        StudyModule.class.getSimpleName()
                )));
    }
}
