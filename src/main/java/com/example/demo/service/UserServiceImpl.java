package com.example.demo.service;

import com.example.demo.mapper.UserMapper;
import com.example.demo.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @ProjectName demo
 * @ClassName UserServiceImpl
 * @Description TODO
 * @Author Lyn
 * @Date 2020/12/8 15:26
 * @Version 1.0
 * @Function
 */

@Service
public class UserServiceImpl implements UserService {

    @SuppressWarnings("all")
    @Autowired
    UserMapper userMapper;


    @Override
    public List<Users> checksUsername(String username) {
        return userMapper.checksUsername(username);
    }

    @Override
    public List<Users> checkEmail(String email) {
        return userMapper.checkEmail(email);
    }

    @Override
    public int register(String username, String password, String salt, String mail, String avatar) {
        return userMapper.register(username, password, salt, mail, avatar);
    }

    @Override
    public Users login(String username) {
        return userMapper.login(username);
    }

    @Override
    public Users getSession(String username) {
        return userMapper.getSession(username);
    }

    @Override
    public Map<String, Object> getUserInfo(String username) {
        return userMapper.getUserInfo(username);
    }
}
