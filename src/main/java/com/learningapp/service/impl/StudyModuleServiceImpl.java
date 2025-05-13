///*
//package com.learningapp.service.impl;
//
//import com.learningapp.dto.StudyModuleDTO;
//import com.learningapp.dto.request.StudyModuleRequest;
//import com.learningapp.dto.response.StudyModuleResponse;
//import com.learningapp.entity.StudyModuleEntity;
//import com.learningapp.repository.StudyModuleRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.Objects;
//
//@Service
//public class StudyModuleServiceImpl {
//
//    private final StudyModuleRepository studyModuleRepository;
//
//    @Autowired
//    public StudyModuleServiceImpl(final StudyModuleRepository studyModuleRepository) {
//        this.studyModuleRepository = studyModuleRepository;
//    }
//
//    public StudyModuleResponse save(final StudyModuleRequest studyModuleRequest) {
//
//        if (Objects.nonNull(studyModuleRequest)) {
//            final StudyModuleEntity studyModuleEntity = new StudyModuleEntity();
//            studyModuleEntity.setDescription(studyModuleRequest.getDescription());
//            studyModuleEntity.setName(studyModuleRequest.getName());
//            studyModuleRepository.save(studyModuleEntity);
//        }
//        return ;
//    }
//}
//*/
