package com.example.asswrkspt.web.exceptions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.asswrkspt.common.logging.Sink;
import com.example.asswrkspt.web.dto.response.ErrorResponse;
import com.example.asswrkspt.web.dto.response.WorkSpotHttpResponse;
import com.fasterxml.jackson.databind.exc.InvalidTypeIdException;

import javax.validation.ConstraintDefinitionException;
import javax.validation.ConstraintViolationException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @Autowired
    private Sink sink;

    @NotNull
    private static ResponseEntity<WorkSpotHttpResponse<?>> getErrorResponse(ErrorCode errorCode,
        String errorMessage,
        HttpStatus httpStatus) {
        ErrorResponse error = ErrorResponse
            .builder()
            .code(errorCode)
            .message(errorMessage)
            .build();
        WorkSpotHttpResponse<?> response = WorkSpotHttpResponse.builder().errors(Collections.singletonList(error)).build();
        return new ResponseEntity<>(response, httpStatus);
    }

    @ExceptionHandler(InvalidTypeIdException.class)
    public ResponseEntity<?> handleException(InvalidTypeIdException e) {
        sink.error("Error occurred", WrkSpotErrorCode.INVALID_REQUEST, e);
        return createInvalidMetricErrorResponse();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleException(MethodArgumentNotValidException e) {
        sink.error("Error occurred", WrkSpotErrorCode.INVALID_REQUEST, e);
        WorkSpotHttpResponse<?> response = WorkSpotHttpResponse.builder().errors(processFieldErrors(e.getBindingResult().getAllErrors())).build();
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<?> handle(MissingServletRequestParameterException e) {
        sink.error("Error occurred", WrkSpotErrorCode.INVALID_REQUEST, e);
        return getErrorResponse(WrkSpotErrorCode.INVALID_REQUEST, String.format("Missing parameter %s", e.getParameterName()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(WrkSpotCommonException.class)
    public ResponseEntity<?> handle(WrkSpotCommonException e) {
        sink.error("Error occurred", e.getErrorCode(), e);
        return getErrorResponse(e.getErrorCode(), e.getMessage(), e.getErrorCode() instanceof WrkSpotErrorCode ?
            ((WrkSpotErrorCode) e.getErrorCode()).getHttpStatus() : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(RequestValidationException.class)
    public ResponseEntity<?> handle(RequestValidationException e) {
        sink.error("Error occurred", WrkSpotErrorCode.INVALID_REQUEST, e);
        WorkSpotHttpResponse<?> response = WorkSpotHttpResponse.builder().errors(processMetricValidationErrors(e.getValidationResponse().getErrors())).build();
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<?> handleGeneralException(HttpMessageNotReadableException ex) {
        sink.error("Error occurred", WrkSpotErrorCode.INVALID_REQUEST, ex);
        return getErrorResponse(WrkSpotErrorCode.INVALID_REQUEST, "Some error occurred while processing the request.", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ResponseEntity<?> handleInvalidContentType(HttpMediaTypeNotSupportedException ex) {
        sink.error("Error occurred", WrkSpotErrorCode.INVALID_REQUEST, ex);
        return getErrorResponse(WrkSpotErrorCode.INVALID_REQUEST, "Invalid media type. Supported content type is JSON", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGeneralException(Exception ex) {
        sink.error("Error occurred", WrkSpotErrorCode.GENERIC_ERROR, ex);
        return getErrorResponse(WrkSpotErrorCode.GENERIC_ERROR, "Some error occurred while processing the request.", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<?> handleGeneralException(ConstraintViolationException ex) {
        sink.error("Error occurred", WrkSpotErrorCode.INVALID_REQUEST, ex);
        return getErrorResponse(WrkSpotErrorCode.INVALID_REQUEST, ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<?> handleGeneralException(HttpRequestMethodNotSupportedException ex) {
        sink.error("Error occurred", WrkSpotErrorCode.METHOD_NOT_ALLOWED, ex);
        return getErrorResponse(WrkSpotErrorCode.METHOD_NOT_ALLOWED, ex.getMessage(), HttpStatus.METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler(ConstraintDefinitionException.class)
    public ResponseEntity<?> handleGeneralException(ConstraintDefinitionException ex) {
        sink.error("Error occurred", WrkSpotErrorCode.INVALID_REQUEST, ex);
        return getErrorResponse(WrkSpotErrorCode.INVALID_REQUEST, ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    private List<ErrorResponse> processMetricValidationErrors(List<RequestValidationError> validationErrors) {
        List<ErrorResponse> errors = new ArrayList<>();
        for (RequestValidationError fieldError : validationErrors) {
            ErrorResponse error = ErrorResponse
                .builder()
                .code(fieldError.getMetricErrorCode())
                .message(fieldError.getMessage())
                .build();
            errors.add(error);
        }
        return errors;
    }

    private static List<ErrorResponse> processFieldErrors(List<ObjectError> fieldErrors) {
        List<ErrorResponse> errors = new ArrayList<>();
        for (ObjectError fieldError : fieldErrors) {
            ErrorResponse error = ErrorResponse
                .builder()
                .code(WrkSpotErrorCode.INVALID_REQUEST)
                .message(fieldError.getDefaultMessage())
                .build();
            errors.add(error);
        }
        return errors;
    }

    @NotNull
    private static ResponseEntity<WorkSpotHttpResponse<?>> createInvalidMetricErrorResponse() {
        String message = "Invalid type identifier.";

        ErrorResponse errorResponse = ErrorResponse
            .builder()
            .code(WrkSpotErrorCode.INVALID_REQUEST)
            .message(message)
            .build();
        WorkSpotHttpResponse<?> response = WorkSpotHttpResponse.builder().errors(Collections.singletonList(errorResponse)).build();
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

}
