package com.aimaginarium.exception;

import lombok.Builder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.Map;

@Builder
public record ErrorDetails(
        LocalDateTime timestamp,
        String path,
        Map<String, String> errors) {

    public static ResponseEntity<ErrorDetails> getResponseEntityErrorsMap(final String path,
                                                                          final HttpStatus status,
                                                                          final Map<String, String> errors) {
        ErrorDetails errorDetails = ErrorDetails.builder()
                .timestamp(LocalDateTime.now())
                .path(path)
                .errors(errors)
                .build();
        return new ResponseEntity<>(errorDetails, status);
    }
}
