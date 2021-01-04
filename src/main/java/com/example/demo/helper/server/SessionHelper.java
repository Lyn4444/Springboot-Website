package com.example.demo.helper.server;


import com.example.demo.entity.Users;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @ProjectName demo
 * @ClassName SessionHelper
 * @Description TODO
 * @Author Lyn
 * @Date 2020/12/10 10:30
 * @Version 1.0
 * @Function
 */

public class SessionHelper {

    /**
     * 会话登录
     *
     * @param request 请求
     * @param user    用户
     * @return {@link HttpSession}
     */
    public static HttpSession sessionLogin(HttpServletRequest request, Users user) {
        HttpSession session = request.getSession();
        session.setAttribute("username", user.getUsername());
        session.setAttribute("email", user.getEmail());
        session.setAttribute("avatar", user.getAvatar());
        session.setAttribute("isAdmin", user.getIsAdmin());
        session.setAttribute("status", "login");
        return session;
    }

}
