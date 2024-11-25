package com.example.project.user.data;

import lombok.Data;
import lombok.experimental.Accessors;

import jakarta.validation.constraints.NotBlank;

@Data
@Accessors(chain = true)
public class SignInData {
    @NotBlank
    private String name;
    @NotBlank
    private String password;
}