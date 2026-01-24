package com.learningapp.service;

import com.learningapp.dto.APIResponseContent;

public interface OpenRouterService {
    APIResponseContent generate(String content);
}
