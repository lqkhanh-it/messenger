package com.example.project.user.data;

import lombok.Data;
import lombok.experimental.Accessors;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Data
@Accessors(chain = true)
public class SignUpData {
    @NotBlank
    private String name;
    @NotBlank
    private String password;
    @Email
    private String email;
}