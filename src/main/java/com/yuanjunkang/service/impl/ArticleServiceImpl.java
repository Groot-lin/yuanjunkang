package com.yuanjunkang.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yuanjunkang.mapper.ArticleMapper;
import com.yuanjunkang.pojo.Article;
import com.yuanjunkang.pojo.ArticleDoc;
import cn.hutool.core.util.StrUtil;
import com.yuanjunkang.pojo.Paging;
import com.yuanjunkang.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

import static com.yuanjunkang.constants.RedisConstants.ARTICLE_CLASS;
import static com.yuanjunkang.constants.RedisConstants.ARTICLE_VIEWS;
@Async
@EnableScheduling
@Service
public class ArticleServiceImpl implements ArticleService {
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    ArticleMapper articleMapper;
    @Override
    public Paging<ArticleDoc> selectAll(int currentpage, int pagesize) {
        int begin = (currentpage-1)*pagesize;
        int size = pagesize;
        List<Article> articles = articleMapper.selectAll(begin,size);
        List<ArticleDoc> articleDocs = new ArrayList<>();
        for(int i = 0;i<articles.size();i++){
            ArticleDoc articleDoc = new ArticleDoc(articles.get(i));
            articleDocs.add(articleDoc);
        }
        //按浏览数递减排序
        Collections.sort(articleDocs);

        Paging<ArticleDoc> pages = new Paging<>();
        pages.setTotalcount(size);
        pages.setRows(articleDocs);
        return pages;
    }

    @Override
    public Article selectOne(int id) {
        String key = ARTICLE_CLASS+id;
        String articleJson = stringRedisTemplate.opsForValue().get(key);
        if(StrUtil.isNotEmpty(articleJson)){
            Article article = JSONObject.parseObject(articleJson, Article.class);
            return article;
        }
        Article article = articleMapper.selectById(id);
        if(article == null){
            return null;
        }
        stringRedisTemplate.opsForValue().set(key, JSON.toJSONString(article));
        return article;
    }

    @Override
    public void updateViews(int id) {
        //1.从redis中取得浏览量
        String cacheViews = String.valueOf(stringRedisTemplate.opsForHash().get(ARTICLE_VIEWS,String.valueOf(id)));
        //2.如果redis中没有则从数据库中取得
        if(StrUtil.isEmpty(cacheViews)||cacheViews==null||cacheViews.equals("null")){
            cacheViews = String.valueOf(articleMapper.selectViews(id));
        }
        cacheViews = String.valueOf(Integer.parseInt(cacheViews)+1);
        stringRedisTemplate.opsForHash().put(ARTICLE_VIEWS,String.valueOf(id),cacheViews);
    }

    @Scheduled(cron = "0 0/1 * * * ?")
    @Override
    public void updateViewsToDB() {
        //1.去数据库中将所有id查出来
        Integer count = articleMapper.selectCount();
        List<Article> articles = articleMapper.selectAll(0, count);
        System.out.println("aaa");
        //2.从redis中查出来以后直接修改
        for (Article article:articles){
            Integer id = article.getId();
            Object value = stringRedisTemplate.opsForHash().get(ARTICLE_VIEWS,String.valueOf(id));

            if(value==null)
                continue;
            int views = Integer.parseInt(String.valueOf(value));
            articleMapper.updateViews(id,views);
        }
    }

}
