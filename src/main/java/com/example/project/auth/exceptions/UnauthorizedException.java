package com.example.project.auth.exceptions;

import com.example.project.base.exceptions.ServiceException;

public class UnauthorizedException extends ServiceException {
    public UnauthorizedException() {
        super("Unauthorized", 401);
    }
}