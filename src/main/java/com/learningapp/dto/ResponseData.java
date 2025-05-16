package com.learningapp.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.learningapp.constants.CoreConstants;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@Getter
public class ResponseData {

    private Object data;

    private List<ErrorDetail> errorDetails;

    private String status;

    @Builder.Default
    private String timestamp = LocalDateTime.now().toString();

    public String getStatus() {
        return (CollectionUtils.isEmpty(errorDetails))
                ? CoreConstants.RESPONSE_STATUS.SUCCESS
                : CoreConstants.RESPONSE_STATUS.ERROR;
    }
}
