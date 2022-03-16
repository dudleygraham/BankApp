package com.learning.config;


import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.learning.entity.ApiResponse;

@RestControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler(RuntimeException.class)
    public ApiResponse handleNotFoundException(RuntimeException ex) {
        ApiResponse apiResponse = new ApiResponse(400, "Bad request", null);
        return apiResponse;
    }

}
