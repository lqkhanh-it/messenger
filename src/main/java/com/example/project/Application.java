package com.example.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.project.user.controllers.UserController;

@SpringBootApplication
@RestController
public class Application {
    public static void main(String[] args) {
      SpringApplication.run(Application.class, args);
    }
}