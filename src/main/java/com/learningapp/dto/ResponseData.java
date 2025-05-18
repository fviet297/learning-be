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

/**
 * Generic response wrapper class for API responses.
 * This class provides a standardized structure for all API responses,
 * including success and error cases.
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@Getter
public class ResponseData {

    /**
     * The actual data payload of the response
     */
    private Object data;

    /**
     * List of error details if any errors occurred
     */
    private List<ErrorDetail> errorDetails;

    /**
     * The status of the response (SUCCESS or ERROR)
     */
    private String status;

    /**
     * Timestamp of when the response was generated
     */
    @Builder.Default
    private String timestamp = LocalDateTime.now().toString();

    /**
     * Gets the status of the response.
     * Returns SUCCESS if there are no errors, ERROR otherwise.
     *
     * @return the status of the response
     */
    public String getStatus() {
        return (CollectionUtils.isEmpty(errorDetails))
                ? CoreConstants.RESPONSE_STATUS.SUCCESS
                : CoreConstants.RESPONSE_STATUS.ERROR;
    }
}
