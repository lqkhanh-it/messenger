package com.example.project.base.entries;

import lombok.Getter;

@Getter
public class Result {
    private int code;
    private String message;
    private Object data;

    private Result setResult(int code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
        return this;
    }

    public Result success() {
        return setResult(200, "Successful!", null);
    }

    public Result success(Object data) {
        return setResult(200, "Successful!", data);
    }

    public Result fail(Object data, String message) {
        return setResult(400, message, data);
    }

    public Result fail(Object data, String message, int code) {
        return setResult(code, message, data);
    }

    public Result fail(String message, int code) {
        return setResult(code, message, null);
    }
}