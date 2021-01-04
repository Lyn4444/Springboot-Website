package com.example.demo.service;

import com.example.demo.entity.NewsAttitude;

import javax.annotation.Resource;

/**
 * @ProjectName demo
 * @InterfaceName NewsAttitudeService
 * @Description TODO
 * @Author Lyn
 * @Date 2020/12/16 0:40
 * @Version 1.0
 * @Function
 */

@Resource
public interface NewsAttitudeService {

    int up(int id, String username, String key_id);

    int down(int id, String username, String key_id);

}
