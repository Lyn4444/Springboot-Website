package com.example.demo.service;

import com.example.demo.entity.Subscription;
import com.example.demo.mapper.SubscriptionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @ProjectName demo
 * @ClassName SubscriptionServiceImpl
 * @Description TODO
 * @Author Lyn
 * @Date 2020/12/15 22:02
 * @Version 1.0
 * @Function
 */

@Service
public class SubscriptionServiceImpl implements SubscriptionService{

    @SuppressWarnings("all")
    @Autowired
    SubscriptionMapper subscriptionMapper;


    @Override
    public List<Map<String, Object>> getSubscribedInfomationFlowCard(String username) {
        return subscriptionMapper.getSubscribedInfomationFlowCard(username);
    }

    @Override
    public List<Map<String, Object>> getSubscriptionList(String username) {
        return subscriptionMapper.getSubscriptionList(username);
    }

    @Override
    public int InsertSubscription(int type, String username) {
        return subscriptionMapper.InsertSubscription(type, username);
    }

    @Override
    public int DeleteSubscription(int type, String username) {
        return subscriptionMapper.DeleteSubscription(type, username);
    }

}
