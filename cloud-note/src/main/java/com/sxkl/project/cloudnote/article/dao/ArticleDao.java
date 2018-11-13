package com.sxkl.project.cloudnote.article.dao;

import com.sxkl.project.cloudnote.article.entity.Article;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ArticleDao extends MongoRepository<Article, String> {
}
