package com.example.demo.service;


import java.util.List;
import java.util.Map;

/**
 * @ProjectName demo
 * @InterfaceName SubscriptionService
 * @Description TODO
 * @Author Lyn
 * @Date 2020/12/15 22:02
 * @Version 1.0
 * @Function
 */

public interface SubscriptionService {

    List<Map<String, Object>> getSubscribedInfomationFlowCard(String username);

    List<Map<String, Object>> getSubscriptionList(String username);

    int InsertSubscription(int type, String username);

    int DeleteSubscription(int type, String username);

}
