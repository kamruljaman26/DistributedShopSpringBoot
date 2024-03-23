package com.shop.article.controller;

import com.shop.article.dto.ArticleRequest;
import com.shop.article.dto.ArticleResponse;
import com.shop.article.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createArticle(@RequestBody ArticleRequest articleRequest) {
        articleService.createArticle(articleRequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ArticleResponse> getArticles() {
        return articleService.getAllArticles();
    }
}
