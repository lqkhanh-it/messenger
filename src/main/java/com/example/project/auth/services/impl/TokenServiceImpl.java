package com.example.project.auth.services.impl;

import com.example.project.auth.services.TokenService;
import com.example.project.auth.exceptions.TokenException;
import io.jsonwebtoken.*;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class TokenServiceImpl implements TokenService {
    private static final String SECRECT = "YOUR SECRET + 64 BIT > OVJPIDSFWEcvoOIDF1-232''F]WE=F23=-4VXCNONSFIOEIWI023";

    private byte[] getSecret() {
        return Base64.getEncoder().encode(SECRECT.getBytes());
    }

    private Map<String, Object> buildPayloadOnlyId(long id) {
        return new HashMap<String, Object>() {
            {
                put("id", id);
            }
        };
    }

    private String buildToken(Object subject, Map<String, Object> payload, int hours) {
        return Jwts.builder().setClaims(payload).setSubject(subject.toString()).setExpiration(new Date(new Date().getTime() + (hours * 36000000)))
                .signWith(SignatureAlgorithm.HS256, getSecret()).compact();
    }

    @Override
    public String generate(Object subject, long id) {
        return buildToken(subject, buildPayloadOnlyId(id), 1);
    }

    @Override
    public String generate(Object subject, long id, int hours) {
        return buildToken(subject, buildPayloadOnlyId(id), hours);
    }

    @Override
    public String generate(Object subject, Map<String, Object> payload) {
        return buildToken(subject, payload, 1);
    }

    @Override
    public String generate(Object subject, Map<String, Object> payload, int hours) {
        return buildToken(subject, payload, hours);
    }

    @Override
    public Jws<Claims> parse(Object subject, String token) {
        try {
            return Jwts.parser()
                .requireSubject(subject.toString())
                .setSigningKey(getSecret())
                .parseClaimsJws(token);
        } catch (JwtException e) {
            throw new TokenException();
        }
    }

}