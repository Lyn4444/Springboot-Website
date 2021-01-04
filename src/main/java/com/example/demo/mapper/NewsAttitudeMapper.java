package com.example.demo.mapper;

import com.example.demo.entity.NewsAttitude;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * @ProjectName demo
 * @InterfaceName NewsAttitudeMapper
 * @Description TODO
 * @Author Lyn
 * @Date 2020/12/16 0:39
 * @Version 1.0
 * @Function
 */

@Mapper
public interface NewsAttitudeMapper {

    @Insert("replace into newsattitude (`news_id`, `user_id`, `attitude`, `key_id`) VALUES (#{id}, (select id from users where username = #{username}), 1, #{key_id})")
    int up(int id, String username, String key_id);

    @Insert("replace into newsattitude (`news_id`, `user_id`, `attitude`, `key_id`) VALUES (#{id}, (select id from users where username = #{username}), 0, #{key_id})")
    int down(int id, String username, String key_id);


}
