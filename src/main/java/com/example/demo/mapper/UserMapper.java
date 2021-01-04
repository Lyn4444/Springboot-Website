package com.example.demo.mapper;

import com.example.demo.entity.Users;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * @ProjectName demo
 * @InterfaceName UserMapper
 * @Description TODO
 * @Author Lyn
 * @Date 2020/12/8 12:03
 * @Version 1.0
 * @Function
 */

public interface UserMapper {

    /**
     * 检查用户名
     *
     * @param username 用户名
     * @return {@link List<Users>}
     */
    @Select("select * from users where username like #{username}")
    List<Users> checksUsername(String username);


    /**
     * 检查电子邮件
     *
     * @param email 电子邮件
     * @return {@link List<Users>}
     */
    @Select("select * from users where email like #{email}")
    List<Users> checkEmail(String email);

    /**
     * 注册
     *
     * @param username 用户名
     * @param password 密码
     * @param salt     盐
     * @param mail     邮件
     * @param avatar   头像
     * @return int
     */
    @Insert("insert into users(`username`, `password`, `salt`, `email`, `avatar`, `isAdmin`) values(#{username}, #{password}, #{salt}, #{mail}, #{avatar}, '0')")
    int register(String username, String password, String salt, String mail, String avatar);

    /**
     * 登录
     *
     * @param username 用户名
     * @return {@link Users}
     */
    @Select("select password, salt from users where username = #{username}")
    Users login(String username);

    /**
     * 获得会话
     *
     * @param username 用户名
     * @return {@link Users}
     */
    @Select("select username, email, avatar, isAdmin from users where username = #{username}")
    Users getSession(String username);

    @Select("select username, email, avatar, isAdmin from users where username = #{username}")
    Map<String, Object> getUserInfo(String username);
}
