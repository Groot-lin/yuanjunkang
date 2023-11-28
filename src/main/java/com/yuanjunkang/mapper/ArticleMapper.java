package com.yuanjunkang.mapper;

import com.yuanjunkang.pojo.Article;
import com.yuanjunkang.pojo.ArticleDoc;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import javax.jws.WebService;
import java.util.List;

@Mapper
public interface ArticleMapper {
    //分页查询所有文章
    @Select("select * from article limit #{begin},#{size}")
    List<Article> selectAll(@Param("begin") int begin, @Param("size") int size);

    //根据主键查询某文章
    @Select("select * from article where id=#{id}")
    Article selectById(@Param("id")int id);

    //阅读量增加
    @Update("update article set views=#{newviews} where id=#{id}")
    void updateViews(@Param("id")int id,@Param("newviews")int newviews);

    //获取浏览量
    @Select("select views from article where id=#{id}")
    Integer selectViews(@Param("id")int id);

    //获取表中有几篇文章
    @Select("select COUNT(*) from article")
    Integer selectCount();
}
