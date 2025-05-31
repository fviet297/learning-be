package com.learningapp.dto.response;

import lombok.Data;

@Data
public class Choice {
    private Object logprobs;
    private String finishReason;
    private String nativeFinishReason;
    private Integer index;
    private MessageResponse message;
} 