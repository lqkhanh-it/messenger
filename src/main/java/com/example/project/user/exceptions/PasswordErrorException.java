package com.example.project.user.exceptions;

import com.example.project.base.exceptions.ServiceException;

public class PasswordErrorException extends ServiceException {
    public PasswordErrorException() {
        super(10400, "Wrong password");
    }
}