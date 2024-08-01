package com.example.asswrkspt.web.dto.response;

import java.util.List;

import org.springframework.util.CollectionUtils;

import com.example.asswrkspt.web.exceptions.RequestValidationError;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Builder
@Setter
public class ValidationResponseDTO {

    private List<RequestValidationError> errors;

    public boolean isSuccess() {
        return CollectionUtils.isEmpty(errors);
    }

}
