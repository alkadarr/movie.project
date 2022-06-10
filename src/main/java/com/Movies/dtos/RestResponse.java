package com.Movies.dtos;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class RestResponse<T> {
    private final T data;
    private String message;
    private String status;

    public RestResponse(T data, String message, String status) {
        this.data = data;
        this.message = message;
        this.status = status;
    }

    public RestResponse(String message, String status) {
        this.data = null;
        this.message = message;
        this.status = status;
    }

    public RestResponse(T data) {
        this.data = data;
    }
}
