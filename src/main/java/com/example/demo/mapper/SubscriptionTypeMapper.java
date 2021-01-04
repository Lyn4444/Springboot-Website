package com.example.demo.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * @ProjectName demo
 * @InterfaceName SubscriptionTypeMapper
 * @Description TODO
 * @Author Lyn
 * @Date 2020/12/16 2:16
 * @Version 1.0
 * @Function
 */

@Mapper
public interface SubscriptionTypeMapper {

    @Select("select * from subscriptiontype")
    List<Map<String, Object>> getAllSubscription();

}
