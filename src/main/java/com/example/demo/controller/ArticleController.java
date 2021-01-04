package com.example.demo.controller;

import com.example.demo.exception.ArticleAttitudeException;
import com.example.demo.exception.ArticleException;
import com.example.demo.helper.dataProcess.ArticleHelper;
import com.example.demo.entity.NewsVote;
import com.example.demo.helper.dataProcess.JSONHelper;
import com.example.demo.helper.model.ResultModel;
import com.example.demo.service.NewsAttitudeService;
import com.example.demo.service.NewsContentService;
import com.example.demo.service.NewsService;
import com.example.demo.service.NewsVoteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * @ProjectName demo
 * @ClassName ArticleController
 * @Description TODO
 * @Author Lyn
 * @Date 2020/12/15 23:06
 * @Version 1.0
 * @Function
 */

@RestController
public class ArticleController {

    private static  final Logger logger= LoggerFactory.getLogger(UserController.class);

    @SuppressWarnings("all")
    @Autowired
    NewsService newsService;

    @SuppressWarnings("all")
    @Autowired
    NewsVoteService newsVoteService;

    @SuppressWarnings("all")
    @Autowired
    NewsContentService newsContentService;

    @SuppressWarnings("all")
    @Autowired
    NewsAttitudeService newsAttitudeService;
/*
    @RequestMapping(value = "getArticleDetails/{id}", method = RequestMethod.GET)
    public ResultModel getArticleDetails(@PathVariable String id) {
        try {
            if (newsContentService.isArticle(Integer.parseInt(id)) > 0) {
                Map<String, Object> temp = newsService.getTitle(Integer.parseInt(id));
                Map<String, Object> time = new HashMap<>();
                time.put("time", temp.get("time"));
                Map<String, Object> title = new HashMap<>();
                title.put("title", temp.get("title"));
                Map<String, Object> summary = new HashMap<>();
                summary.put("summary", temp.get("summary"));
                Map<String, Object> orign = new HashMap<>();
                orign.put("orign", temp.get("orign"));
                List<Map<String, Object>> data = newsContentService.getArticlePages(Integer.parseInt(id));
                data.add(time);
                data.add(title);
                data.add(summary);
                data.add(orign);
                return ResultModel.isOk().data(JSONHelper.getArticlePages(data));
            } else {
                return ResultModel.isFail("The id of News doesn't exist");
            }
        } catch (Exception e) {
            return ResultModel.isFail(e.toString());
        }
    }
*/
    @RequestMapping(value = "getArticleDetails/{id}", method = RequestMethod.GET)
    public ResultModel getArticleDetails(@PathVariable String id) throws ArticleException {
        if (newsContentService.isArticle(Integer.parseInt(id)) > 0) {
            Map<String, Object> temp = newsService.getTitle(Integer.parseInt(id));
            List<Map<String, Object>> data = newsContentService.getArticlePages(Integer.parseInt(id));
            List<Map<String, Object>> dataRes = ArticleHelper.getArticleContent(id, temp, data);
            return ResultModel.success().data(JSONHelper.getArticlePages(dataRes));
        } else {
            throw new ArticleException("没有这篇新闻");
        }
    }

    @RequestMapping(value = "getVote/{id}", method = RequestMethod.GET)
    public ResultModel getVote(@PathVariable String id) throws ArticleException {
        if (newsVoteService.isVote(Integer.parseInt(id)) > 0) {
            NewsVote vote = newsVoteService.getVote(Integer.parseInt(id));
            Map<String, Object> dataRes = ArticleHelper.getVoteResult(vote);
            return ResultModel.success().data(JSONHelper.getData(dataRes));
        } else {
            throw new ArticleException("这篇新闻暂时没有投票");
        }
    }

    @RequestMapping(value = "upThisArticle/{id}", method = RequestMethod.POST)
    public ResultModel upThisArticle(@PathVariable String id, HttpSession session) throws ArticleAttitudeException {
        String status = (String) session.getAttribute("status");
        if (status.equals("login")) {
            String username = (String) session.getAttribute("username");
            String key_id = id + "/" + username;
            if (newsAttitudeService.up(Integer.parseInt(id), username, key_id) != 0) {
            ;
            } else {
                throw new ArticleAttitudeException("点赞失败");
            }

            if (newsVoteService.addUpVote(Integer.parseInt(id)) != 0) {
                return ResultModel.success();
            }
            else {
                throw new ArticleAttitudeException("点赞失败");
            }
        } else {
            throw new NullPointerException();
        }
    }

    @RequestMapping(value = "downThisArticle/{id}", method = RequestMethod.POST)
    public ResultModel downThisArticle(@PathVariable String id, HttpSession session) throws ArticleAttitudeException {
        String status = (String) session.getAttribute("status");
        if (status.equals("login")) {
            String username = (String) session.getAttribute("username");
            String key_id = id + "/" + username;
            if (newsAttitudeService.down(Integer.parseInt(id), username, key_id) != 0) {
                ;
            } else {
                throw new ArticleAttitudeException("反对失败");
            }
            if (newsVoteService.addDownVote(Integer.parseInt(id)) != 0) {
                return ResultModel.success();
            }
            else {
                throw new ArticleAttitudeException("反对失败");
            }
        } else {
            throw new NullPointerException();
        }
    }

}
