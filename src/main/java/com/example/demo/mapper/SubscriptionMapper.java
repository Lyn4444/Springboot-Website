package com.example.demo.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example.demo.entity.Subscription;

import java.util.List;
import java.util.Map;

/**
 * @ProjectName demo
 * @InterfaceName SubscriptionMapper
 * @Description TODO
 * @Author Lyn
 * @Date 2020/12/15 22:02
 * @Version 1.0
 * @Function
 */

@Mapper
public interface SubscriptionMapper {

    @Select("select news.news_id, news.title, news.summary, news.time, news.orign, news.type, subscription.user_id, subscription.type from news, subscription, users where news.type = subscription.type and subscription.user_id = users.id and users.username = #{username} ORDER BY RAND() LIMIT 10")
    List<Map<String, Object>> getSubscribedInfomationFlowCard(String username);

    @Select("select subscriptiontype.name from subscription, subscriptiontype, users where users.username = #{username} and users.id = subscription.user_id and subscription.type = subscriptiontype.type")
    List<Map<String, Object>> getSubscriptionList(String username);

    @Insert("insert into subscription(`user_id`, type) select (select users.id from users where username = #{username}), #{type} from dual where not EXISTS (select user_id, type from subscription where subscription.user_id = (select users.id from users where username = #{username}) and subscription.type = #{type}) and #{type} <= (select count(*) from subscriptiontype) and #{type} >= 1")
    int InsertSubscription(int type, String username);

    @Delete("delete from subscription where type = #{type} and user_id = (select id from users where username = #{username})")
    int DeleteSubscription(int type, String username);
}
