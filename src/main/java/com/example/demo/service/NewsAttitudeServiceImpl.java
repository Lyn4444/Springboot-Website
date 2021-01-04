package com.example.demo.service;

import com.example.demo.entity.NewsAttitude;
import com.example.demo.mapper.NewsAttitudeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ProjectName demo
 * @ClassName NewsAttitudeImpl
 * @Description TODO
 * @Author Lyn
 * @Date 2020/12/16 0:40
 * @Version 1.0
 * @Function
 */

@Service
public class NewsAttitudeServiceImpl implements NewsAttitudeService{

    @SuppressWarnings("all")
    @Autowired
    NewsAttitudeMapper newsAttitudeMapper;


    @Override
    public int up(int id, String username, String key_id) {
        return newsAttitudeMapper.up(id, username, key_id);
    }

    @Override
    public int down(int id, String username, String key_id) {
        return newsAttitudeMapper.down(id, username, key_id);
    }
}
