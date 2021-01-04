package com.example.demo.entity;


public class NewsAttitude {

  private long userId;
  private long newsId;
  private long attitude;


  public long getUserId() {
    return userId;
  }

  public void setUserId(long userId) {
    this.userId = userId;
  }


  public long getNewsId() {
    return newsId;
  }

  public void setNewsId(long newsId) {
    this.newsId = newsId;
  }


  public long getAttitude() {
    return attitude;
  }

  public void setAttitude(long attitude) {
    this.attitude = attitude;
  }

}
