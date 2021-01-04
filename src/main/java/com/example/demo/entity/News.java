package com.example.demo.entity;


public class News {

  private long newsId;
  private String title;
  private String summary;
  private String time;
  private String orign;
  private int type;


  public long getNewsId() {
    return newsId;
  }

  public void setNewsId(long newsId) {
    this.newsId = newsId;
  }


  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }


  public String getSummary() {
    return summary;
  }

  public void setSummary(String summary) {
    this.summary = summary;
  }


  public String getTime() {
    return time;
  }

  public void setTime(String time) {
    this.time = time;
  }


  public String getOrign() {
    return orign;
  }

  public void setOrign(String orign) {
    this.orign = orign;
  }


  public int getType() {
    return type;
  }

  public void setType(int type) {
    this.type = type;
  }

}
