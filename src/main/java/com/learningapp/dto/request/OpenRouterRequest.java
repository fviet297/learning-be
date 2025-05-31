package com.learningapp.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class OpenRouterRequest {
    private String model;
    private List<MessageRequest> messages;
}