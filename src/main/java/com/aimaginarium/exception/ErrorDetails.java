package com.aimaginarium.exception;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.Map;

@Data
@Builder
public class ErrorDetails {

    private LocalDateTime timestamp;
    private String path;
    private Map<String, String> errors;

    public static ResponseEntity<ErrorDetails> getResponseEntityErrorsMap(String path,
                                                                          HttpStatus status,
                                                                          Map<String, String> errors) {
        ErrorDetails errorDetails = ErrorDetails.builder()
                .timestamp(LocalDateTime.now())
                .path(path)
                .errors(errors)
                .build();
        return new ResponseEntity<>(errorDetails, status);
    }
}
