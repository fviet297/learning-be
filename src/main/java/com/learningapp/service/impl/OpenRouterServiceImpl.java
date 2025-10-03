package com.learningapp.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.learningapp.dto.request.FlashcardRequest;
import com.learningapp.enums.CreationEnum;
import com.learningapp.exception.BusinessException;
import com.learningapp.service.OpenRouterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.Prompt;
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
    public FlashcardRequest generate(final String text, final CreationEnum creationEnum) {
        try {
            Prompt prompt = new Prompt(new SystemMessage("Dựa vào nội dung message hãy tạo cặp câu hỏi câu trả lời tương ứng "),
                    new UserMessage(text));
            return  chatClient
                    .prompt(prompt)
                    .call().entity(FlashcardRequest.class);

        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException("Error generating.");
        }
    }

}
