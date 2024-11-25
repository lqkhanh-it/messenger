package com.example.project.auth.exceptions;

public class TokenException extends RuntimeException {
    public TokenException() {
        super("False token");
    }
}