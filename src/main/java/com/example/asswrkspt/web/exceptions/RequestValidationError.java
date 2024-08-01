package com.example.asswrkspt.web.exceptions;

import lombok.Builder;
import lombok.Data;


@Builder
@Data
public class RequestValidationError {
    private WrkSpotErrorCode metricErrorCode;
    private String message;
}

