package com.example.demo.service;

import com.example.demo.mapper.NewsContentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @ProjectName demo
 * @ClassName NewsContentServiceImpl
 * @Description TODO
 * @Author Lyn
 * @Date 2020/12/15 23:05
 * @Version 1.0
 * @Function
 */

@Service
public class NewsContentServiceImpl implements NewsContentService{

    @SuppressWarnings("all")
    @Autowired
    NewsContentMapper newsContentMapper;

    @Override
    public int isArticle(int id) {
        return newsContentMapper.isArticle(id);
    }

    @Override
    public List<Map<String, Object>> getArticlePages(int id) {
        return newsContentMapper.getArticlePages(id);
    }
}
