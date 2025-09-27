package com.learningapp.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OpenRouterRequest {
    private String model;
    private List<MessageRequest> messages;
    private Map<String, Object> response_format;
}
