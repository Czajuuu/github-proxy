package com.example.github_proxy;

public class ErrorResponse {
    private final int status;
    private final String message;

    ErrorResponse(int status, String message)
    {
        this.status = status;
        this.message = message;
    }
    public int getStatus() { return status;}
    public String getMessage() {return message;}
}
