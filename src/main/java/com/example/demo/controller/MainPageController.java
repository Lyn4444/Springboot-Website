package com.example.demo.controller;

import com.example.demo.entity.JsonKeyword;
import com.example.demo.entity.JsonSubscription;
import com.example.demo.helper.dataProcess.JSONHelper;
import com.example.demo.exception.RangeException;
import com.example.demo.helper.model.ResultModel;
import com.example.demo.service.NewsService;
import com.example.demo.service.SubscriptionService;
import com.example.demo.service.SubscriptionTypeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.validation.ValidationException;
import java.util.List;
import java.util.Map;

/**
 * @ProjectName demo
 * @ClassName ArticleController
 * @Description TODO
 * @Author Lyn
 * @Date 2020/12/15 21:07
 * @Version 1.0
 * @Function
 */

@RestController
public class MainPageController {

    private static  final Logger logger= LoggerFactory.getLogger(UserController.class);

    @SuppressWarnings("all")
    @Autowired
    NewsService newsService;

    @SuppressWarnings("all")
    @Autowired
    SubscriptionService subscriptionService;

    @SuppressWarnings("all")
    @Autowired
    SubscriptionTypeService subscriptionTypeService;

    /**
     * 被关键字信息流程卡
     * 搜索
     *
     * @param jsonKeyword json关键字
     * @return {@link ResultModel}
     */
    @RequestMapping(value = "getInfomationFlowCardByKeyword", method = RequestMethod.POST)
    public ResultModel getInfomationFlowCardByKeyword(@Valid @RequestBody JsonKeyword jsonKeyword)
            throws ValidationException {
        String keyWord = jsonKeyword.getKeyWord();
        List<Map<String, Object>> data = newsService.search(keyWord);

        return ResultModel.success().data(JSONHelper.getDataMulti(data));
    }


    /**
     * 得到所有信息流程卡
     *
     * @return {@link ResultModel}
     */
    @RequestMapping(value = "getAllInfomationFlowCard", method = RequestMethod.GET)
    public ResultModel getAllInfomationFlowCard() {
        List<Map<String, Object>> data = newsService.getAllInfomationFlowCard();
        return ResultModel.success().data(JSONHelper.getDataMulti(data));
    }

    /**
     * 获得订阅信息流程卡
     *
     * @param session 会话
     * @return {@link ResultModel}
     */
    @RequestMapping(value = "getSubscribedInfomationFlowCard", method = RequestMethod.GET)
    public ResultModel getSubscribedInfomationFlowCard(HttpSession session) {
        if (session.getAttribute("status") == null) {
            List<Map<String, Object>> data = newsService.getAllInfomationFlowCard();
            return ResultModel.success().data(JSONHelper.getDataMulti(data));
        } else {
            String username = (String) session.getAttribute("username");
            List<Map<String, Object>> data = subscriptionService.getSubscribedInfomationFlowCard(username);
            return ResultModel.success().data(JSONHelper.getDataMulti(data));
        }
    }


    /**
     * 得到所有订阅
     *
     * @return {@link ResultModel}
     */
    @RequestMapping(value = "getAllSubscription", method = RequestMethod.POST)
    public ResultModel getAllSubscription() {
        List<Map<String, Object>> SubscriptionList = subscriptionTypeService.getAllSubscription();
        return ResultModel.success().data(JSONHelper.getArticlePages(SubscriptionList));
    }

    /**
     * 插入用户订阅
     *
     * @param jsonSubscription json订阅
     * @param session          会话
     * @return {@link ResultModel}
     * @throws RangeException 范围的例外
     */
    @RequestMapping(value = "insertUserSubscription", method = RequestMethod.POST)
    public ResultModel insertUserSubscription(@Valid @RequestBody JsonSubscription jsonSubscription,
                                              HttpSession session) throws RangeException {
        String username = (String) session.getAttribute("username");
        String type = jsonSubscription.getType();
        if (subscriptionService.InsertSubscription(Integer.parseInt(type), username) == 1) {
            return ResultModel.success();
        } else {
            throw new RangeException("订阅已经存在或者输入参数错误");
        }
    }


    /**
     * 删除用户订阅
     *
     * @param session          会话
     * @param jsonSubscription json订阅
     * @return {@link ResultModel}
     */
    @RequestMapping(value = "deleteUserSubscription", method = RequestMethod.POST)
    public ResultModel deleteUserSubscription(HttpSession session,
                                              @Valid @RequestBody JsonSubscription jsonSubscription)
            throws RangeException {
        String username = (String) session.getAttribute("username");
        String type = jsonSubscription.getType();
        if (subscriptionService.DeleteSubscription(Integer.parseInt(type), username) == 1) {
            return ResultModel.success();
        } else {
            throw new RangeException("订阅已经删除或者输入参数错误");
        }
    }

    /**
     * 获取用户订阅
     *
     * @param session 会话
     * @return {@link ResultModel}
     */
    @RequestMapping(value = "getUserSubscription", method = RequestMethod.POST)
    public ResultModel getUserSubscription(HttpSession session) {
        String status = (String) session.getAttribute("status");
        if (status.equals("login")) {
            String username = (String) session.getAttribute("username");
            List<Map<String, Object>> list = subscriptionService.getSubscriptionList(username);
            return ResultModel.success().data(JSONHelper.getArticlePages(list));
        } else {
            throw new NullPointerException();
        }
    }



}
