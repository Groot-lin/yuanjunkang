package com.yuanjunkang.pojo;

import lombok.Data;

import java.sql.Date;

@Data
public class Article {
    private Integer id;
    private String title;
    private String summary;
    private String content;
    private Date date;
    private long views;
    private String author;
    private String affiliati;
    private String headshot;

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", summer='" + summary + '\'' +
                ", content='" + content + '\'' +
                ", date=" + date +
                ", views=" + views +
                ", author='" + author + '\'' +
                ", affiliati='" + affiliati + '\'' +
                ", headshot='" + headshot + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummer() {
        return summary;
    }

    public void setSummer(String summer) {
        this.summary = summer;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String context) {
        this.content = context;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public long getViews() {
        return views;
    }

    public void setViews(long views) {
        this.views = views;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAffiliati() {
        return affiliati;
    }

    public void setAffiliati(String affiliati) {
        this.affiliati = affiliati;
    }

    public String getHeadshot() {
        return headshot;
    }

    public void setHeadshot(String headshot) {
        this.headshot = headshot;
    }
}
