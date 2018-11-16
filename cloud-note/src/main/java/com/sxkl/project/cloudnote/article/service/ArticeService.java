package com.sxkl.project.cloudnote.article.service;

import com.sxkl.project.cloudnote.article.entity.Article;
import com.sxkl.project.cloudnote.common.OperationResult;
import org.springframework.data.domain.Page;

public interface ArticeService {

    OperationResult save(Article article);

    OperationResult delete(String id);

    OperationResult update(Article article);

    Article findById(String id);

    Page<Article> findPage(Article article, int page, int size);

    long countByNoteId(String noteId);

    long countByFlagId(String flagId);
}
