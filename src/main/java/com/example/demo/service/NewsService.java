package com.example.demo.service;

import java.util.List;
import java.util.Map;

/**
 * @ProjectName demo
 * @InterfaceName NewsService
 * @Description TODO
 * @Author Lyn
 * @Date 2020/12/15 21:04
 * @Version 1.0
 * @Function
 */

public interface NewsService {

    List<Map<String, Object>> search(String key);

    List<Map<String, Object>> getAllInfomationFlowCard();

    Map<String, Object> getTitle(int id);

}
