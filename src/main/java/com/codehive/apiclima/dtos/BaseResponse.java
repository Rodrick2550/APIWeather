package com.codehive.apiclima.dtos;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


@Getter
@Builder
public class BaseResponse<T> {
    private T data;
    private String message;
    private Boolean success;
    private HttpStatus httpStatus;


}
