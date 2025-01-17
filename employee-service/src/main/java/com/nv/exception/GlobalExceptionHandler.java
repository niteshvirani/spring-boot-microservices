package com.nv.exception;

import com.nv.dto.ErrorResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> resourceNotFoundExceptionHandler(ResourceNotFoundException e) {
        ErrorResponseDto errorResponseDto = ErrorResponseDto.builder()
                .message(e.getMessage())
                .success(false)
                .status(HttpStatus.NOT_FOUND)
                .build();
        return new ResponseEntity<>(errorResponseDto, HttpStatus.NOT_FOUND);
    }
}
