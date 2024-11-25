package com.example.project.auth.services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;

import java.util.Map;

public interface TokenService {
    String generate(Object subject, long id);

    String generate(Object subject, long id, int hours);

    String generate(Object subject, Map<String, Object> payload);

    String generate(Object subject, Map<String, Object> payload, int hours);

    Jws<Claims> parse(Object subject, String token);
}