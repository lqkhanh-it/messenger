package com.example.project.user.controllers;

import com.example.project.auth.annotation.Auth;
import com.example.project.base.entries.Result;
import com.example.project.user.data.SignInData;
import com.example.project.user.data.SignUpData;
import com.example.project.user.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/signin")
    public Result signIn(@RequestBody @Validated SignInData data) {
        return new Result().success(userService.signIn(data));
    }

    @PostMapping("/signup")
    public Result signUp(@RequestBody @Validated SignUpData data) {
        userService.signUp(data);
        return new Result().success();
    }

    @GetMapping
    public Result get(@Auth(required = false) Long userId) {
        return new Result().success(userId);
    }

    @GetMapping("/{userId}")
    public Result get(@PathVariable("userId") long userId) {
        return new Result().success(userService.getById(userId));
    }

    @GetMapping("/")
    public Result list(@Auth Long userId) {
        return new Result().success(userService.list());
    }
}