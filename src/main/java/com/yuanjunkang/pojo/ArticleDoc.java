package com.yuanjunkang.pojo;

import lombok.Data;

import java.nio.charset.StandardCharsets;
import java.sql.Date;

@Data
public class ArticleDoc implements Comparable<ArticleDoc>{
    private Integer id;
    private String title;
    private String summmay;
    private Date date;
    private long views;
    private String author;
    private String affiliati;
    private String headshot;

    public ArticleDoc(Article article){
        this.id = article.getId();
        this.title = article.getTitle();
        this.summmay = article.getSummer();
        this.date = article.getDate();
        this.views = article.getViews();
        this.author = article.getAuthor();
        this.affiliati = article.getAffiliati();
        this.headshot = article.getHeadshot();
    }

    @Override
    public int compareTo(ArticleDoc articleDoc) {
        return (int)(articleDoc.views-this.views);
    }
}
