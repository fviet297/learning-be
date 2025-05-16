package com.learningapp.service.impl;

import com.learningapp.constants.CoreConstants;
import com.learningapp.dto.request.StudyModuleRequest;
import com.learningapp.dto.response.StudyModuleResponse;
import com.learningapp.entity.StudyModuleEntity;
import com.learningapp.exception.NotFoundException;
import com.learningapp.mapper.StudyModuleMapper;
import com.learningapp.repository.StudyModuleRepository;
import com.learningapp.service.StudyModuleService;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
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

        StudyModuleEntity studyModuleEntity = studyModuleMapper.toEntity(studyModuleRequest);
        studyModuleEntity = studyModuleRepository.save(studyModuleEntity);
        return studyModuleMapper.toResponse(studyModuleEntity);
    }

    @Override
    public StudyModuleResponse getById(@NotBlank final String id) {
        final StudyModuleEntity studyModuleEntity = this.findById(id);
        return studyModuleMapper.toResponse(studyModuleEntity);
    }

    @Override
    public StudyModuleEntity findById(@NotBlank final String id) {
        return studyModuleRepository
                .findByIdAndIsDeleteFalse(id)
                .orElseThrow(() ->
                        new NotFoundException(
                                String.format(
                                        CoreConstants.MESSAGE_ERROR.NOT_FOUND_ENTITY,
                                        StudyModuleEntity.class.getSimpleName(),
                                        id
                                )));
    }
}
