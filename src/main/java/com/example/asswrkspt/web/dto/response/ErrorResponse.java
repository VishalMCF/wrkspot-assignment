package com.example.asswrkspt.web.dto.response;

import com.example.asswrkspt.web.exceptions.ErrorCode;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorResponse {
    private ErrorCode code;
    private String message;
}
