package com.learningapp.dto.request;

import com.learningapp.dto.MessageDTO;

public class MessageRequest extends MessageDTO {
    public MessageRequest(final String role, final String content) {
        super(role, content, null, null);
    }
}
