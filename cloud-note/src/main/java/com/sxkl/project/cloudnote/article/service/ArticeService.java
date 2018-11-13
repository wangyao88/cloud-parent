package com.sxkl.project.cloudnote.article.service;

import com.sxkl.project.cloudnote.article.entity.Article;
import com.sxkl.project.cloudnote.common.OperationResult;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ArticeService {

    OperationResult save(Article article);

    OperationResult delete(String id);

    OperationResult forceDelete(String id);

    OperationResult update(Article article);

    Article findById(String id);

    List<Article> findAll();

    Page<Article> findPage(int page, int size);

    int countByNoteId(String noteId);
}
