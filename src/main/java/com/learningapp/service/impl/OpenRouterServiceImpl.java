package com.learningapp.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.learningapp.dto.APIResponseContent;
import com.learningapp.enums.CreationEnum;
import com.learningapp.exception.BusinessException;
import com.learningapp.service.OpenRouterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.converter.BeanOutputConverter;
import org.springframework.stereotype.Service;

@Service
public class OpenRouterServiceImpl implements OpenRouterService {

    Logger LOG = LoggerFactory.getLogger(OpenRouterServiceImpl.class);

    private final ChatClient chatClient;
    private final ObjectMapper objectMapper;

    public OpenRouterServiceImpl(ChatClient.Builder chatClientBuilder, ObjectMapper objectMapper) {
        this.chatClient = chatClientBuilder.build();
        this.objectMapper = objectMapper;
    }

    @Override
    public APIResponseContent generate(final String text) {
        try {
            var outputConverter = new BeanOutputConverter<>(APIResponseContent.class);
            
            APIResponseContent apiResponseContent = chatClient
                    .prompt()
                    .user(u -> u.text(CreationEnum.COMBINED.getCommand())
                            .param("command", text)
                            .param("format", outputConverter.getFormat()))
                    .call().entity(APIResponseContent.class);

            if (apiResponseContent != null) {
                LOG.info("Successfully generated content using AI for input length: {}", text.length());
                LOG.debug("AI Response: {}", apiResponseContent);
            }

            return apiResponseContent;
        } catch (Exception e) {
            LOG.error("Error occurred while generating content from OpenRouter: {}", e.getMessage(), e);
            throw new BusinessException("Lỗi trong quá trình tạo nội dung từ AI: " + e.getMessage());
        }
    }

}
