package com.yuanjunkang.controller;

import com.yuanjunkang.pojo.*;
import com.yuanjunkang.service.impl.ArticleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("article")
public class ArticleController {
    @Autowired
    ArticleServiceImpl articleService;

    /**
     * 分页查询展示页
     * @param currentPage
     * @param pageSize
     * @return
     */
    @PostMapping("/selectbypage")
    public Paging<ArticleDoc> searchAll(@RequestBody SendBody sendBody){
        System.out.println(sendBody);
        int currentPage = sendBody.getCurrentpage();
        int pageSize = sendBody.getPageSize();
        return articleService.selectAll(currentPage,pageSize);
    }

    /**
     * 浏览具体文章并增加阅读量
     * @param articleDoc
     * @return
     */
    @GetMapping("/{id}")
    public Article selectOne(@PathVariable("id") Integer id){
        //增加阅读量
        articleService.updateViews(id);
        return articleService.selectOne(id);
    }
}
