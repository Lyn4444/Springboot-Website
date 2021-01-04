package com.example.demo.service;

import com.example.demo.entity.NewsVote;
import org.apache.ibatis.annotations.Update;

/**
 * @ProjectName demo
 * @InterfaceName NewsVoteService
 * @Description TODO
 * @Author Lyn
 * @Date 2020/12/15 23:33
 * @Version 1.0
 * @Function
 */

public interface NewsVoteService {

    int isVote(int id);

    NewsVote getVote(int id);

    int addUpVote(int id);

    int addDownVote(int id);

}
