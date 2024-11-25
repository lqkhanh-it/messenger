package com.example.project.base.handlers;

import com.example.project.base.entries.Result;
import com.example.project.base.exceptions.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;

@RestControllerAdvice
public class ExceptionsHandler {

    private final Logger logger = LoggerFactory.getLogger(ExceptionsHandler.class);

    @ExceptionHandler(Exception.class)
    public Result exception(Exception e) {
        logger.error(e.getMessage(), e);
        return new Result().fail("Error", 500);
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public Result notFoundException() {
        return new Result().fail("Not found", 404);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public Result httpRequestMethodNotSupportedException() {
        return new Result().fail("Method not allowed", 405);
    }

    @ExceptionHandler({HttpMessageNotReadableException.class, MissingServletRequestParameterException.class, MissingServletRequestPartException.class, BindException.class})
    public Result parameterException() {
        return new Result().fail("Parameter error", 403);
    }

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public Result maxUploadSizeExceededException() {
        return new Result().fail("File is too large", 403);
    }

    @ExceptionHandler(ServiceException.class)
    public Result serviceException(ServiceException e) {
        return new Result().fail(e.getData(), e.getMessage(), e.getCode());
    }

}