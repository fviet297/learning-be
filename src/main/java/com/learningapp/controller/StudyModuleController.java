package com.learningapp.controller;

import com.learningapp.constants.Constants;
import com.learningapp.dto.PageCustom;
import com.learningapp.dto.ResponseData;
import com.learningapp.dto.request.StudyModuleRequest;
import com.learningapp.dto.response.StudyModuleProjection;
import com.learningapp.dto.response.StudyModuleResponse;
import com.learningapp.service.StudyModuleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/study-modules")
public class StudyModuleController {

    private final StudyModuleService studyModuleService;

    @Autowired
    public StudyModuleController(StudyModuleService studyModuleService) {
        this.studyModuleService = studyModuleService;
    }

    @PostMapping
    public ResponseEntity<ResponseData> createStudyModule(@Valid @RequestBody StudyModuleRequest studyModuleRequest) {
        final StudyModuleResponse studyModuleResponse = studyModuleService.create(studyModuleRequest);
        return ResponseEntity.ok(ResponseData.builder().data(studyModuleResponse).build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseData> updateStudyModule(@PathVariable String id, @Valid @RequestBody StudyModuleRequest studyModuleRequest) {
        final StudyModuleResponse studyModuleResponse = studyModuleService.update(id,studyModuleRequest);
        return ResponseEntity.ok(ResponseData.builder().data(studyModuleResponse).build());
    }

    @GetMapping
    public ResponseEntity<ResponseData> getAllStudyModules(
            @RequestParam(value = Constants.QUERY.PAGE, defaultValue = Constants.QUERY.PAGE_DEFAULT) final int page,
            @RequestParam(value = Constants.QUERY.SIZE, defaultValue = Constants.QUERY.SIZE_DEFAULT) final int size,
            @RequestParam(value = Constants.QUERY.SORT, defaultValue = Constants.QUERY.UPDATE_ORDER) final String sort
    ) {
        final Pageable pageable = PageRequest.of(page, size, Sort.by(sort).descending());
        final Page<StudyModuleProjection> pageStudyModules = studyModuleService.getPageStudyModules(pageable);

        if (!pageStudyModules.hasContent()) {
            return ResponseEntity.noContent().build();
        }
        final PageCustom<StudyModuleProjection> pageCustom = PageCustom.from(pageStudyModules);
        return ResponseEntity.ok(ResponseData.builder().data(pageCustom).build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseData> getStudyModuleById(@PathVariable String id) {
        final StudyModuleResponse studyModuleResponse = studyModuleService.getById(id);
        return ResponseEntity.ok(ResponseData.builder().data(studyModuleResponse).build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseData> deleteModule(@PathVariable final String id) {
        return ResponseEntity.ok(studyModuleService.deleteModule(id));
    }
}