package com.example.project.controllers;

import com.neko.seed.auth.annotation.Auth;
import com.neko.seed.base.entity.Result;
import com.neko.seed.user.data.SignInData;
import com.neko.seed.user.data.SignUpData;
import com.neko.seed.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/signIn")
    public Result signIn(@RequestBody @Validated SignInData data) {
        return new Result().success(userService.signIn(data));
    }

    @PostMapping("/signUp")
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

    @GetMapping("/list")
    public Result list(@Auth Long userId) {
        return new Result().success(userService.list());
    }
}