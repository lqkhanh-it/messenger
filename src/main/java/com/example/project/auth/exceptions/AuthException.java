package com.example.project.auth.exceptions;

import com.example.project.base.exceptions.ServiceException;

public class AuthException extends ServiceException {
    public AuthException() {
        super("Authentication failed", 403);
    }
}