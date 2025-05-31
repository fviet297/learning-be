package com.learningapp.service;

import com.learningapp.enums.CreationEnum;

import java.util.List;
import java.util.Map;

public interface OpenRouterService {
    List<Map<String, Object>> generate(String content, CreationEnum creationEnum);
}
