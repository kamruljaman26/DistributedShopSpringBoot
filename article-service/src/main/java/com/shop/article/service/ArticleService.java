package com.shop.article.service;

import com.shop.article.dto.ArticleRequest;
import com.shop.article.dto.ArticleResponse;
import com.shop.article.model.Article;
import com.shop.article.repository.ArticleRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    public void createArticle(ArticleRequest articleRequest) {
        Article article = Article.builder()
                .name(articleRequest.getName())
                .description(articleRequest.getDescription())
                .build();

        articleRepository.save(article);
        log.info("Article {} is saved", article.getId());
    }

    public List<ArticleResponse> getAllArticles() {
        List<Article> articleList = articleRepository.findAll();
        return articleList.stream()
                .map(this::mapToArticleResponse)
                .collect(Collectors.toList());
    }

    private ArticleResponse mapToArticleResponse(Article article) {
        return ArticleResponse.builder()
                .id(article.getId())
                .name(article.getName())
                .description(article.getDescription())
                .build();
    }

    public ArticleResponse getArticleById(Long id) {
        Article article = articleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Article not found"));
        return mapToArticleResponse(article);
    }

    public ArticleResponse updateArticle(Long id, ArticleRequest articleRequest) {
        Article article = articleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Article not found"));

        article.setName(articleRequest.getName());
        article.setDescription(articleRequest.getDescription());

        articleRepository.save(article);
        return mapToArticleResponse(article);
    }

    public void deleteArticle(Long id) {
        articleRepository.deleteById(id);
    }
}

