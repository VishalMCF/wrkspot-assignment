package com.example.asswrkspt.web.dto.response;

import java.util.List;

import lombok.Builder;

@Builder
public class WorkSpotHttpResponse <T> {
    private T data;
    private String message;
    private boolean success;
    private List<ErrorResponse> errors;
}
