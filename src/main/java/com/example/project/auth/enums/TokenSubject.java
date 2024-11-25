package com.example.project.auth.enums;

public enum TokenSubject {
    ACCESS("access"), REFRESH("refresh");

    private final String type;

    TokenSubject(String type) {
        this.type = type;
    }

    public String type() {
        return type;
    }
}