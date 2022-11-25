package com.Movies.configs;

import lombok.Data;

@Data
public class RestResponse {
    private boolean success = true;
    private String message = "";
    private Object data;

    public RestResponse(boolean success, String message, Object data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }

    public RestResponse(Object data) {
        this.data = data;
    }

    public RestResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public RestResponse() {
    }
}
