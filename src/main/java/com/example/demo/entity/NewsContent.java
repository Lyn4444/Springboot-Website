package com.example.demo.entity;


public class NewsContent {

  private long newsId;
  private long pages;
  private String pageSentence;


  public long getNewsId() {
    return newsId;
  }

  public void setNewsId(long newsId) {
    this.newsId = newsId;
  }


  public long getPages() {
    return pages;
  }

  public void setPages(long pages) {
    this.pages = pages;
  }


  public String getPageSentence() {
    return pageSentence;
  }

  public void setPageSentence(String pageSentence) {
    this.pageSentence = pageSentence;
  }

}
