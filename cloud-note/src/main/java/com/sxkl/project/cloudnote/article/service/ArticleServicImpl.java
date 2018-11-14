package com.sxkl.project.cloudnote.article.service;

import com.sxkl.project.cloudnote.article.dao.ArticleDao;
import com.sxkl.project.cloudnote.article.entity.Article;
import com.sxkl.project.cloudnote.common.OperationResult;
import com.sxkl.project.cloudnote.note.entity.Note;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleServicImpl implements ArticeService {

    @Autowired
    private ArticleDao articleDao;

    @Override
    public OperationResult save(Article article) {
        return null;
    }

    @Override
    public OperationResult delete(String id) {
        return null;
    }

    @Override
    public OperationResult forceDelete(String id) {
        return null;
    }

    @Override
    public OperationResult update(Article article) {
        return null;
    }

    @Override
    public Article findById(String id) {
        return null;
    }

    @Override
    public List<Article> findAll() {
        return null;
    }

    @Override
    public Page<Article> findPage(int page, int size) {
        return null;
    }

    @Override
    public long countByNoteId(String noteId) {
        Article article = new Article();
        article.getNoteIds().add(noteId);
        return articleDao.count(Example.of(article));
    }
}
