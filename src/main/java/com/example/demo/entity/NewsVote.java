package com.example.demo.entity;


public class NewsVote {

  private long newsId;
  private long up;
  private long down;


  public long getNewsId() {
    return newsId;
  }

  public void setNewsId(long newsId) {
    this.newsId = newsId;
  }


  public long getUp() {
    return up;
  }

  public void setUp(long up) {
    this.up = up;
  }


  public long getDown() {
    return down;
  }

  public void setDown(long down) {
    this.down = down;
  }

}
