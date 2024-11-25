package com.example.project.user.services;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.project.user.data.SignInData;
import com.example.project.user.data.SignUpData;
import com.example.project.user.entries.User;
import com.example.project.user.views.SignInView;
import org.springframework.stereotype.Service;

@Service    
public interface UserService extends IService<User> {
    SignInView signIn(SignInData data);
    void signUp(SignUpData data);
}