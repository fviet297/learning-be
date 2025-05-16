package com.learningapp.exception;

import com.learningapp.dto.ErrorDetail;
import com.learningapp.dto.ResponseData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class AdviceExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(AdviceExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseData> handleSystemException(final Exception ex) {

        log.error("Internal Server Error occurred", ex);
        final ErrorDetail errorDetail = ErrorDetail.builder().errorCode("SYSTEM_ERROR").message("Internal Server Error").build();

        final ResponseData responseData = ResponseData.builder().errorDetails(Collections.singletonList(errorDetail)).build();
        return ResponseEntity.internalServerError().body(responseData);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseData> handleValidationException(final MethodArgumentNotValidException ex) {

        final List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        final List<ErrorDetail> errorDetails = fieldErrors.stream().map(i -> {
            final StringBuilder msg = new StringBuilder();
            msg.append(i.getField()).append(" ").append(i.getDefaultMessage());
            return ErrorDetail.builder().errorCode("VALIDATION_ERROR").message(msg.toString()).build();
        }).collect(Collectors.toList());

        final ResponseData responseData = ResponseData.builder().errorDetails(errorDetails).build();
        return ResponseEntity.badRequest().body(responseData);
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ResponseData> handleBusinessException(final BusinessException ex) {

        final ResponseData responseData = ResponseData.builder().errorDetails(
                Collections.singletonList(ErrorDetail.builder().errorCode("BUSINESS_ERROR").message(ex.getMessage()).build()
                )).build();
        return ResponseEntity.badRequest().body(responseData);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ResponseData> handleNotFoundException(final NotFoundException ex) {

        final ResponseData responseData = ResponseData.builder().errorDetails(
                Collections.singletonList(ErrorDetail.builder().errorCode("NOT_FOUND_ERROR").message(ex.getMessage()).build()
                )).build();
        return ResponseEntity.badRequest().body(responseData);
    }
}
