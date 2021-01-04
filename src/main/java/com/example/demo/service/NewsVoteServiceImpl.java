package com.example.demo.service;

import com.example.demo.entity.NewsVote;
import com.example.demo.mapper.NewsVoteMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ProjectName demo
 * @ClassName NewsVoteServiceImpl
 * @Description TODO
 * @Author Lyn
 * @Date 2020/12/15 23:34
 * @Version 1.0
 * @Function
 */

@Service
public class NewsVoteServiceImpl implements NewsVoteService {

    @SuppressWarnings("all")
    @Autowired
    NewsVoteMapper newsVoteMapper;

    @Override
    public int isVote(int id) {
        return newsVoteMapper.isVote(id);
    }

    @Override
    public NewsVote getVote(int id) {
        return newsVoteMapper.getVote(id);
    }

    @Override
    public int addUpVote(int id) {
        return newsVoteMapper.addUpVote(id);
    }

    @Override
    public int addDownVote(int id) {
        return newsVoteMapper.addDownVote(id);
    }
}
