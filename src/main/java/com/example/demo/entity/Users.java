package com.example.demo.entity;

/**
 * @ProjectName demo
 * @ClassName User
 * @Description TODO
 * @Author Lyn
 * @Date 2020/12/8 15:43
 * @Version 1.0
 * @Function
 */

public class Users {

    private String username;

    private String password;

    private String salt;

    private String email;

    private String avatar;
    
    private int isAdmin;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(int isAdmin) {
        this.isAdmin = isAdmin;
    }

    @Override
    public String toString() {
        return "Users{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", salt='" + salt + '\'' +
                ", email='" + email + '\'' +
                ", avatar='" + avatar + '\'' +
                ", isAdmin=" + isAdmin +
                '}';
    }

    public Users() {
    }

    public Users(String username, String password, String salt, String email, String avatar, int isAdmin) {
        this.username = username;
        this.password = password;
        this.salt = salt;
        this.email = email;
        this.avatar = avatar;
        this.isAdmin = isAdmin;
    }

}

