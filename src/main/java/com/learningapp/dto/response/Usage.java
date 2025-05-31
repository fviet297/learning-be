package com.learningapp.dto.response;

import lombok.Data;

@Data
public class Usage {
    private Integer promptTokens;
    private Integer completionTokens;
    private Integer totalTokens;
    private TokenDetails promptTokensDetails;
    private TokenDetails completionTokensDetails;
} 