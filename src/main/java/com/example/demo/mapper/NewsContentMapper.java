package com.example.demo.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * @ProjectName demo
 * @InterfaceName NewsContentMapper
 * @Description TODO
 * @Author Lyn
 * @Date 2020/12/15 23:04
 * @Version 1.0
 * @Function
 */

@Mapper
public interface NewsContentMapper {

    @Select("select count(distinct news_id) from newscontent where news_id = #{id}")
    int isArticle(int id);

    @Select("SELECT pages, pageSentence from newscontent where news_id = #{id} ORDER BY pages ASC ")
    List<Map<String, Object>> getArticlePages(int id);

}
