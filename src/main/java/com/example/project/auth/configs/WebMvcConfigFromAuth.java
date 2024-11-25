package com.example.project.auth.configs;

import com.example.project.auth.annotation.resolver.AuthMethodArgumentResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WebMvcConfigFromAuth implements WebMvcConfigurer {
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(authMethodArgumentResolver());
    }

    @Bean
    public AuthMethodArgumentResolver authMethodArgumentResolver() {
        return new AuthMethodArgumentResolver();
    }

}