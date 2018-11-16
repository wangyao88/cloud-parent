package com.sxkl.project.cloudnote.article.service;

import com.sxkl.project.cloudnote.article.dao.ArticleDao;
import com.sxkl.project.cloudnote.article.entity.Article;
import com.sxkl.project.cloudnote.common.OperationResult;
import com.sxkl.project.cloudnote.flag.entity.Flag;
import org.apache.commons.lang3.StringUtils;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleServicImpl implements ArticeService {

    @Autowired
    private ArticleDao articleDao;
    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public OperationResult save(Article article) {
        try {
            articleDao.save(article);
            return OperationResult.buildSuccess("笔记保存成功");
        } catch (Exception e) {
            return OperationResult.buildFail("笔记保存失败");
        }
    }

    @Override
    public OperationResult delete(String id) {
        try {
            articleDao.deleteById(id);
            return OperationResult.buildSuccess("笔记删除成功");
        } catch (Exception e) {
            return OperationResult.buildFail("笔记删除失败");
        }
    }

    @Override
    public OperationResult update(Article article) {
        return null;
    }

    @Override
    public Article findById(String id) {
        return articleDao.findById(id).orElse(new Article(id));
    }

    @Override
    public Page<Article> findPage(Article article, int page, int size) {
        Document queryObject = new Document();
        Document fieldsObject = new Document();
        fieldsObject.put("id",true);
        fieldsObject.put("title",true);
        Query query = new BasicQuery(queryObject, fieldsObject);
        Criteria criteria = new Criteria();
        query.skip(page*size).limit(size);
        if(!StringUtils.isEmpty(article.getUserId())) {
            query.addCriteria(Criteria.where("userId").is(article.getUserId()));
        }
        if(!StringUtils.isEmpty(article.getTile())) {
            query.addCriteria(Criteria.where("title").alike(Example.of(article.getTile())));
        }
        long count = mongoTemplate.count(query, Flag.class);
        List<Article> articles = mongoTemplate.find(query, Article.class);
        return new PageImpl<Article>(articles,PageRequest.of(page,size),count);
    }

    @Override
    public long countByNoteId(String noteId) {
        Article article = new Article();
        article.getNoteIds().add(noteId);
        return articleDao.count(Example.of(article));
    }

    @Override
    public long countByFlagId(String flagId) {
        Article article = new Article();
        article.getFlagIds().add(flagId);
        return articleDao.count(Example.of(article));
    }
}
