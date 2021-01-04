package com.example.demo;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Connection;
import java.util.Date;
import java.sql.DriverManager;
import java.sql.SQLException;

@SpringBootTest
class NewsWebApplicationTests {

    NewsWebApplicationTests() {}

    @Test()
    void testHash() {
        Date date = new Date();

    }


    @Test
    void contextLoads() {
       Connection con = testSql();
    }


    public Connection testSql() {
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println ("数据库驱动加载成功！hhhhhhhhh");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/news_web?useSSL=false&serverTimezone=Hongkong","root","root");
            System.out.println ("数据库连接成功！hhhhhhhhh");
        } catch (ClassNotFoundException e1) {
            System.out.println ("数据库驱动加载失败！hhhhhhhhh");
        }catch(SQLException e2){
            System.out.println ("数据库连接失败！hhhhhhhhh");
        }
        return con;
    }
}


