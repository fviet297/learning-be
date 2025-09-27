package com.learningapp.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.learningapp.constants.Constants;
import com.learningapp.dto.APIResponseContent;
import com.learningapp.dto.request.MessageRequest;
import com.learningapp.dto.request.OpenRouterRequest;
import com.learningapp.dto.response.ChatCompletionResponse;
import com.learningapp.dto.response.Choice;
import com.learningapp.dto.response.MessageResponse;
import com.learningapp.enums.CreationEnum;
import com.learningapp.exception.BusinessException;
import com.learningapp.service.OpenRouterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class OpenRouterServiceImpl implements OpenRouterService {

    Logger LOG = LoggerFactory.getLogger(OpenRouterServiceImpl.class);

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    @Value("${openrouter.api.key}")
    private String API_KEY;

    @Value("${openrouter.api.url}")
    private String API_URL;

    @Value("${openrouter.api.model}")
    private String MODEL;

    public OpenRouterServiceImpl(RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }

    @Override
    public List<Map<String, Object>> generate(final String text, final CreationEnum creationEnum) {
        try {
            final HttpHeaders headers = buildHeader();

            final OpenRouterRequest requestBody = buildBodyRequest(text, creationEnum);

            final HttpEntity<OpenRouterRequest> requestEntity = new HttpEntity<>(requestBody, headers);

            final ChatCompletionResponse response = restTemplate.postForObject(
                    API_URL,
                    requestEntity,
                    ChatCompletionResponse.class
            );

            LOG.info(response.toString());
            // Extract and parse the response
            if (response != null && response.getChoices() != null) {
                final List<Choice> choices = response.getChoices();
                if (!choices.isEmpty()) {
                    final Choice firstChoice = choices.get(0);
                    final MessageResponse messageContent = firstChoice.getMessage();
                    final String content = messageContent.getContent();
                    APIResponseContent apiResponseContent = objectMapper.readValue(content, APIResponseContent.class);
                    // Parse the JSON string from the content
                    return creationEnum.getType().equals(CreationEnum.FLASHCARD.getType())
                            ? apiResponseContent.getFlashcards()
                            : apiResponseContent.getQuizzes();
                }
            }

            return new ArrayList<>();
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException("Error generating.");
        }
    }

    private OpenRouterRequest buildBodyRequest(String text, CreationEnum creationEnum) {
        final MessageRequest message = new MessageRequest(
                Constants.OPEN_ROUTER.USER_ROLE
                , text + creationEnum.getCommand()
        );

        final List<MessageRequest> messages = new ArrayList<>();
        messages.add(message);

        final OpenRouterRequest requestBody = new OpenRouterRequest(MODEL, messages, Map.of("type", "json_object"));
        return requestBody;
    }

    private HttpHeaders buildHeader() {
        // Prepare headers
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + API_KEY);
        return headers;
    }
}
