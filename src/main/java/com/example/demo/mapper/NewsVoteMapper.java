package com.example.demo.mapper;

import com.example.demo.entity.NewsVote;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * @ProjectName demo
 * @InterfaceName NewsVoteMapper
 * @Description TODO
 * @Author Lyn
 * @Date 2020/12/15 23:32
 * @Version 1.0
 * @Function
 */

@Mapper
public interface NewsVoteMapper {

    @Select("select count(distinct news_id) from newsvote where news_id = #{id}")
    int isVote(int id);

    @Select("select * from newsvote where news_id = #{id}")
    NewsVote getVote(int id);

    @Update("UPDATE newsvote SET up=up+1 WHERE news_id = #{id}")
    int addUpVote(int id);

    @Update("UPDATE newsvote SET down=down+1 WHERE news_id = #{id}")
    int addDownVote(int id);

}
