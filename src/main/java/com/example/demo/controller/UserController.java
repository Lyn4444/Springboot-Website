package com.example.demo.controller;

import com.example.demo.helper.model.ResultModel;
import com.example.demo.helper.server.AvatarHelper;
import com.example.demo.helper.dataProcess.JSONHelper;
import com.example.demo.helper.mail.MailService;
import com.example.demo.helper.mail.RandomUtil;
import com.example.demo.helper.server.SessionHelper;
import com.example.demo.helper.encryption.MD5Util;
import com.example.demo.exception.DecryptException;
import com.example.demo.exception.EmailException;
import com.example.demo.exception.LoginException;
import com.example.demo.exception.RegisterException;
import com.example.demo.entity.Users;
import com.example.demo.service.SubscriptionService;
import com.example.demo.service.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.*;
import java.util.List;
import java.util.Map;


/**
 * @ProjectName demo
 * @ClassName UserController
 * @Description TODO
 * @Author Lyn
 * @Date 2020/12/8 11:35
 * @Version 1.0
 * @Function
 */

@RestController
public class UserController {

    private static final Logger logger= LoggerFactory.getLogger(UserController.class);

    @SuppressWarnings("all")
    @Autowired
    UserService userService;

    @SuppressWarnings("all")
    @Autowired
    SubscriptionService subscriptionService;

    /**
     * 登录
     *
     * @param users   用户
     * @param request 请求
     * @return {@link ResultModel}
     */
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public ResultModel login(@Valid @RequestBody Users users, HttpServletRequest request)
            throws Exception {
        Map<String, Object> decryptResult = JSONHelper.JsonDecrypt(users);
        Users user = userService.login((String) decryptResult.get("username"));
        if (user.getPassword().equals(String.valueOf(MD5Util.getMD5Hash((String) decryptResult.get("password"),
                user.getSalt())))) {
            Users userSession = userService.getSession((String) decryptResult.get("username"));
            HttpSession session = SessionHelper.sessionLogin(request, userSession);
            return ResultModel.success();
        } else {
            throw new LoginException("登录失败");
        }
    }

    /**
     * 发送电子邮件
     *
     * @param users 用户
     * @return {@link ResultModel}
     */
    @RequestMapping(value = "sendmail", method = RequestMethod.POST)
    public ResultModel sendEmail(@Valid @RequestBody Users users) throws Exception {
        Map<String, Object> decryptResult = JSONHelper.JsonDecrypt(users);
        String Verification = RandomUtil.getCode();
        if (userService.checksUsername((String) decryptResult.get("username")).isEmpty()) {
            if (userService.checkEmail((String) decryptResult.get("email")).isEmpty()) {
                MailService.sendMail((String) decryptResult.get("email"), Verification);
            } else {
                throw new EmailException("email已经注册");
            }
        } else {
            throw new EmailException("用户名已经注册");
        }
        return ResultModel.success().data(JSONHelper.getVerification(Verification));
    }



    /**
     * 注册
     *
     * @param users 用户
     * @return {@link ResultModel}
     */
    @RequestMapping(value = "register", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ResultModel register(@Valid @RequestBody Users users)
            throws DecryptException, IOException, RegisterException {
        Map<String, Object> decryptResult = JSONHelper.JsonDecrypt(users);
        String salt = MD5Util.getSalt();
        String hashPassword = MD5Util.getMD5Hash((String) decryptResult.get("password"), salt);
        String avatar = DigestUtils.md5Hex((String) decryptResult.get("email"));
        String targetName = AvatarHelper.AvatarDone(avatar);
        if (userService.register((String) decryptResult.get("username"),
                String.valueOf(hashPassword),
                salt,
                (String) decryptResult.get("email"),
                targetName) == 1) {
            return ResultModel.success();
        } else {
            throw new RegisterException("注册失败");
        }
    }

    /**
     * 获取用户信息
     *
     * @param session 会话
     * @return {@link ResultModel}
     */
    @RequestMapping(value = "getUserInfomation", method = RequestMethod.POST)
    public ResultModel getUserInfomation(HttpSession session) {
        String status = (String) session.getAttribute("status");
        if (status.equals("login")) {
            String username = (String) session.getAttribute("username");
            List<Map<String, Object>> list = subscriptionService.getSubscriptionList(username);
            Map<String, Object> userInfo = userService.getUserInfo(username);
            return ResultModel.success().data(JSONHelper.getDataUserInfo(list, userInfo));
        } else {
            throw new NullPointerException();
        }
    }

    /**
     * 修改用户订阅信息
     *
     * @param jsonParam json参数
     * @param session   会话
     * @return {@link ResultModel}
     */
    /*@RequestMapping(value = "changeUserInfo", method = RequestMethod.POST)
    public ResultModel changeUserInfo(@RequestBody JSONObject jsonParam, HttpSession session) {
        try {
            String status = (String) session.getAttribute("status");
            if (status.equals("login")) {
                int done = 0;
                int keyNum = 0;
                for (String key: jsonParam.keySet()) {
                    keyNum++;
                }
                return ResultModel.isOk();
            } else {
                return ResultModel.isFail("No login, no access");
            }
        } catch (Exception e) {
            return ResultModel.isFail(e.toString());
        }
    }*/


    /**
     * 注销
     *
     * @param session 会话
     * @return {@link ResultModel}
     */
 /*   @RequestMapping(value = "logout", method = RequestMethod.GET)
    @ResponseBody
    public ResultModel logout(HttpSession session, HttpServletResponse response) {
        try {
            String status = (String) session.getAttribute("status");
            if (status.equals("login")) {
                session.removeAttribute("username");
                session.removeAttribute("avatar");
                session.removeAttribute("email");
                session.removeAttribute("isAdmin");
                System.out.println("已注销");
                session.invalidate();
                response.sendRedirect("/index");
                //return "redirect:/index";
                return ResultModel.isOk();
            } else {
                return ResultModel.isFail("logout failure");
            }
        } catch (Exception e) {
            return ResultModel.isFail(e.toString());
        }
    }
*/

}