package com.learningapp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.data.domain.Page;

import java.util.List;

@AllArgsConstructor
@Builder
public class PageCustom<T> {
    @JsonProperty("content")
    private List<T> content;

    @JsonProperty("page")
    private int page;

    @JsonProperty("size")
    private int size;

    @JsonProperty("total_record")
    private long totalRecords;

    @JsonProperty("total_page")
    private int totalPage;

    public static <T> PageCustom<T> from(Page<T> pageData) {
        return PageCustom.<T>builder()
                .content(pageData.getContent())
                .page(pageData.getNumber())
                .size(pageData.getSize())
                .totalPage(pageData.getTotalPages())
                .totalRecords(pageData.getTotalElements())
                .build();
    }

}
