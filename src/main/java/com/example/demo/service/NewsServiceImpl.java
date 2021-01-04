package com.example.demo.service;

import com.example.demo.mapper.NewsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @ProjectName demo
 * @ClassName NewsServiceImpl
 * @Description TODO
 * @Author Lyn
 * @Date 2020/12/15 21:05
 * @Version 1.0
 * @Function
 */

@Service
public class NewsServiceImpl implements NewsService{

    @SuppressWarnings("all")
    @Autowired
    NewsMapper newsMapper;

    @Override
    public List<Map<String, Object>> search(String key) {
        return newsMapper.search('%' + key + '%');
    }

    @Override
    public List<Map<String, Object>> getAllInfomationFlowCard() {
        return newsMapper.getAllInfomationFlowCard();
    }

    @Override
    public Map<String, Object> getTitle(int id) {
        return newsMapper.getTitle(id);
    }
}
