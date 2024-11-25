package com.example.project.auth.annotation.resolver;

import com.example.project.auth.annotation.Auth;
import com.example.project.auth.enums.TokenSubject;
import com.example.project.auth.exceptions.AuthException;
import com.example.project.auth.exceptions.TokenException;
import com.example.project.auth.exceptions.UnauthorizedException;
import com.example.project.auth.services.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

public class AuthMethodArgumentResolver implements HandlerMethodArgumentResolver {

    @Autowired
    private TokenService tokenService;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType().isAssignableFrom(Long.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        String token = webRequest.getHeader("Access-Token");
        if (token != null && token != "") {
            try {
                return tokenService.parse(TokenSubject.ACCESS, token).getBody().get("id");
            } catch (TokenException e) {
                throw new AuthException();
            }
        } else if (!parameter.getParameterAnnotation(Auth.class).required()) {
            return null;
        } else {
            throw new UnauthorizedException();
        }
    }

}