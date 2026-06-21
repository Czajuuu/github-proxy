package com.example.github_proxy;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

@RestControllerAdvice
class GitHubExceptionHandler {

    @ExceptionHandler(HttpClientErrorException.NotFound.class)
    ResponseEntity<ErrorResponse> handleNotFound(HttpClientErrorException.NotFound ex) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponse(404, "GitHub user not found"));
    }
}