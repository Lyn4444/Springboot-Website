package com.example.demo.service;

import com.example.demo.mapper.SubscriptionMapper;
import com.example.demo.mapper.SubscriptionTypeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @ProjectName demo
 * @ClassName SubscriptionTypeServiceImpl
 * @Description TODO
 * @Author Lyn
 * @Date 2020/12/16 2:17
 * @Version 1.0
 * @Function
 */

@Service
public class SubscriptionTypeServiceImpl implements SubscriptionTypeService{

    @SuppressWarnings("all")
    @Autowired
    SubscriptionTypeMapper subscriptionTypeMapper;

    @Override
    public List<Map<String, Object>> getAllSubscription() {
        return subscriptionTypeMapper.getAllSubscription();
    }
}
