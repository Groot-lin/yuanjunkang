package com.yuanjunkang.service;

import com.yuanjunkang.pojo.Article;
import com.yuanjunkang.pojo.ArticleDoc;
import com.yuanjunkang.pojo.Paging;

import java.util.List;

public interface ArticleService {
    /**
     * 搜索出面板所有的文章(分页)
     * @param begin
     * @param size
     * @return
     */
    Paging<ArticleDoc> selectAll(int begin, int size);

    /**
     * 根据主键查看某文章
     * @param id
     * @return
     */
    Article selectOne(int id);

    /**
     * 增加文章阅读量
     * @param id
     */
    void updateViews(int id);

    /**
     * 修改mysql中的浏览量
     */
    void updateViewsToDB();
}
