package com.learningapp.dto.response;

import java.util.List;
import lombok.Data;

@Data
public class ChatCompletionResponse {
    private String id;
    private String provider;
    private String model;
    private String object;
    private Long created;
    private List<Choice> choices;
    private String systemFingerprint;
    private Usage usage;
} 