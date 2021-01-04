package com.example.demo.service;



import com.example.demo.entity.Users;

import java.util.List;
import java.util.Map;

/**
 * @ProjectName demo
 * @InterfaceName UserService
 * @Description TODO
 * @Author Lyn
 * @Date 2020/12/8 12:17
 * @Version 1.0
 * @Function
 */

public interface UserService {

    List<Users> checksUsername(String username);

    List<Users> checkEmail(String email);

    int register(String username, String password, String salt, String mail, String avatr);

    Users login(String username);

    Users getSession(String username);

    Map<String, Object> getUserInfo(String username);

}

