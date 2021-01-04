package com.example.demo.helper.dataProcess;

import com.example.demo.entity.NewsVote;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ProjectName demo
 * @ClassName ArticleHelper
 * @Description TODO
 * @Author Lyn
 * @Date 2020/12/22 2:09
 * @Version 1.0
 * @Function
 */

public class ArticleHelper {

    public static List<Map<String, Object>> getArticleContent(String id, Map<String, Object> temp, List<Map<String, Object>> data) {
        Map<String, Object> time = new HashMap<>();
        time.put("time", temp.get("time"));
        Map<String, Object> title = new HashMap<>();
        title.put("title", temp.get("title"));
        Map<String, Object> summary = new HashMap<>();
        summary.put("summary", temp.get("summary"));
        Map<String, Object> orign = new HashMap<>();
        orign.put("orign", temp.get("orign"));
        data.add(time);
        data.add(title);
        data.add(summary);
        data.add(orign);
        return data;
    }

    public static Map<String, Object> getVoteResult(NewsVote vote) {
        int sumVote = (int) (vote.getUp() + vote.getDown());
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("UpPercent", String.valueOf((int) (vote.getUp()) / (sumVote * 1.0)));
        dataMap.put("downPercent", String.valueOf((int) (vote.getDown()) / (sumVote * 1.0)));
        return dataMap;
    }

}
