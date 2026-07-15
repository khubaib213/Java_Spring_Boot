package com.example.myJar;

import java.util.HashMap;
import java.util.Map;

public class ErrorResponse {

    private int status;
    private String message;
    private Map<String, String> error;
    public ErrorResponse(int status, String message, Map<String, String>error)
    {
        this.status= status;
        this.message= message;
        this.error = error;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setError(Map<String, String> error) {
        this.error = error;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public Map<String, String> getError() {
        return error;
    }
}
