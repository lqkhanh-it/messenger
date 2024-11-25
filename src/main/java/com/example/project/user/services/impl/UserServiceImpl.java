package com.example.project.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.project.auth.enums.TokenSubject;
import com.example.project.auth.services.TokenService;
import com.example.project.base.exceptions.ServiceException;
import com.example.project.user.data.SignInData;
import com.example.project.user.data.SignUpData;
import com.example.project.user.entries.User;
import com.example.project.user.exceptions.PasswordErrorException;
import com.example.project.user.exceptions.UserNotFoundException;
import com.example.project.user.mappers.UserMapper;
import com.example.project.user.services.UserService;
import com.example.project.user.views.SignInView;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private TokenService tokenService;

    @Override
    public SignInView signIn(SignInData data) {
        User user = userMapper.selectOne(new QueryWrapper<User>().select("id,name,password").eq("name", data.getName()));
        if (user != null) {
            if(data.getPassword().equals(user.getPassword())) {
                SignInView signInView = new SignInView();
                signInView.setAccessToken(tokenService.generate(TokenSubject.ACCESS,user.getId()));
                signInView.setRefreshToken(tokenService.generate(TokenSubject.REFRESH,user.getId(),24));
                return signInView;
            } else {
                throw new PasswordErrorException();
            }

        } else {
            throw new UserNotFoundException();
        }
    }

    @Override
    public void signUp(SignUpData data) {
        User user = new User();
        BeanUtils.copyProperties(data,user);
        try {
            userMapper.insert(user);
        } catch (Exception e) {
            throw new ServiceException("用户创建失败");
        }
    }
}