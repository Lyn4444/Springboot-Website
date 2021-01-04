package com.example.demo.helper.dataProcess;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.helper.encryption.RSAService;
import com.example.demo.entity.Users;
import com.example.demo.exception.DecryptException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ProjectName demo
 * @ClassName JSONHelper
 * @Description TODO
 * @Author Lyn
 * @Date 2020/12/9 21:51
 * @Version 1.0
 * @Function
 */

public class JSONHelper {

    /**
     * 获取数据
     *
     * @param param 参数
     * @return {@link String}
     */
    public static JSON getData(Map<String, Object> param) {
        JSONObject jsonObject = new JSONObject();
        for (String key: param.keySet()) {
            jsonObject.put(key, param.get(key));
        }
        return jsonObject;
    }

    /**
     * 得到验证
     *
     * @param Verification 验证
     * @return {@link JSON}
     */
    public static JSON getVerification(String Verification) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("Verification", Verification);
        return jsonObject;
    }

    /**
     * 处理多种数据
     *
     * @param param 参数
     * @return {@link JSON}
     */
    public static JSON getDataMulti(List<Map<String, Object>> param) {
        JSONArray res = new JSONArray();
        for (Map<String, Object> map: param) {
            String id = String.valueOf(map.get("news_id"));
            map.put("detailUrl", "/article/" + id);
            JSONObject temp = new JSONObject();
            for (String key: map.keySet()) {
                temp.put(key, map.get(key));
            }
            res.add(temp);
        }

        return res;
    }

    /**
     * 处理文章页面
     *
     * @param param 参数
     * @return {@link JSON}
     */
    public static JSON getArticlePages(List<Map<String, Object>> param) {
        JSONArray res = new JSONArray();
        for (Map<String, Object> map: param) {
            JSONObject temp = new JSONObject();
            for (String key: map.keySet()) {
                temp.put(key, map.get(key));
            }
            res.add(temp);
        }

        return res;
    }

    /**
     * 处理用户信息获取数据
     *
     * @param list     列表
     * @param userInfo 用户信息
     * @return {@link JSON}
     */
    public static JSON getDataUserInfo(List<Map<String, Object>> list, Map<String, Object> userInfo) {
        JSONArray subscription = new JSONArray();
        JSONArray res = new JSONArray();
        JSONObject jsonObject = new JSONObject();
        for (Map<String, Object> map: list) {
            JSONObject temp = new JSONObject();
            for (String key: map.keySet()) {
                temp.put(key, map.get(key));
            }
            subscription.add(temp);
        }
        for (String key: userInfo.keySet()) {
            jsonObject.put(key, userInfo.get(key));
        }
        res.add(0, subscription);
        res.add(1,jsonObject);
        return res;
    }

    public static Map<String, Object> JsonDecrypt(Users users) throws DecryptException {
        try {
            Map<String, Object> res = new HashMap<>();
            if (users.getUsername() != null) res.put("username", RSAService.decrypt(users.getUsername()));
            if (users.getPassword() != null) res.put("password", RSAService.decrypt(users.getPassword()));
            if (users.getEmail() != null) res.put("email", RSAService.decrypt(users.getEmail()));
            if (users.getAvatar() != null) res.put("avatar", users.getAvatar());
            return res;
        } catch (Exception e) {
            throw new DecryptException("解码失败");
        }

    }
}
