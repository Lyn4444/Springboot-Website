package com.example.demo.service;

import java.util.List;
import java.util.Map;

/**
 * @ProjectName demo
 * @InterfaceName NewsContentService
 * @Description TODO
 * @Author Lyn
 * @Date 2020/12/15 23:05
 * @Version 1.0
 * @Function
 */

public interface NewsContentService {

    int isArticle(int id);

    List<Map<String, Object>> getArticlePages(int id);

}
