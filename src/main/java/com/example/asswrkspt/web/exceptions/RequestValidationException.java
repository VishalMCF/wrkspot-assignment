package com.example.asswrkspt.web.exceptions;

import com.example.asswrkspt.web.dto.response.ValidationResponseDTO;

import lombok.Getter;

@Getter
public class RequestValidationException extends WrkSpotCommonException {

    private final ValidationResponseDTO validationResponse;

    public RequestValidationException(ValidationResponseDTO validationResponse) {
        super(WrkSpotErrorCode.INVALID_REQUEST, "error occurred while validating");
        this.validationResponse = validationResponse;
    }
}
