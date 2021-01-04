package com.example.demo.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * @ProjectName demo
 * @InterfaceName NewsMapper
 * @Description TODO
 * @Author Lyn
 * @Date 2020/12/15 20:54
 * @Version 1.0
 * @Function
 */

@Mapper
public interface NewsMapper {

    @Select("select * from news where title like #{key} or summary like #{key}")
    List<Map<String, Object>> search(String key);

    @Select("SELECT * FROM news ORDER BY RAND() LIMIT 10")
    List<Map<String, Object>> getAllInfomationFlowCard();

    @Select("select time, title, summary, orign from news where news_id = #{id}")
    Map<String, Object> getTitle(int id);

}
