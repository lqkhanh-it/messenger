package com.example.project.user.exceptions;

import com.example.project.base.exceptions.ServiceException;

public class UserNotFoundException extends ServiceException {
    public UserNotFoundException() {
        super(10404, "User Not Found!");
    }
}