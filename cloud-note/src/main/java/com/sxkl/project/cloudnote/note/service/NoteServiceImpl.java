package com.sxkl.project.cloudnote.note.service;

import com.sxkl.project.cloudnote.article.service.ArticeService;
import com.sxkl.project.cloudnote.common.OperationResult;
import com.sxkl.project.cloudnote.job.JobManager;
import com.sxkl.project.cloudnote.note.dao.NoteDao;
import com.sxkl.project.cloudnote.note.entity.Note;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteServiceImpl implements NoteService {

    @Autowired
    private NoteDao noteDao;
    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    private ArticeService articeService;

    @Override
    public OperationResult save(Note note) {
        try {
            boolean exists = noteDao.exists(Example.of(new Note(note.getName())));
            if(exists) {
                return OperationResult.buildFail("笔记本已存在");
            }
            noteDao.save(note);
            return OperationResult.buildSuccess("笔记本保存成功");
        } catch (Exception e) {
            return OperationResult.buildFail("笔记本保存失败");
        }
    }

    @Override
    public OperationResult deleteById(String id) {
        try {
            long count = articeService.countByNoteId(id);
            if(count > 0) {
                return OperationResult.buildFail("笔记本尚关联"+count+"篇文章");
            }
            noteDao.deleteById(id);
            return OperationResult.buildDefaultSuccess();
        } catch (Exception e) {
            return OperationResult.buildFail("笔记本删除失败");
        }
    }

    @Override
    public OperationResult forceDeleteById(String id) {
        try {
            noteDao.deleteById(id);
            JobManager.startUpdateArticleByDeleteNoteJob(id);
            return OperationResult.buildDefaultSuccess();
        } catch (Exception e) {
            return OperationResult.buildFail("笔记本删除失败");
        }
    }

    @Override
    public OperationResult update(Note note) {
        try {
            Query query = new Query(Criteria.where("id").is(note.getId()));
            Update update = new Update();
            update.set("name", note.getName());
            mongoTemplate.updateFirst(query,update,Note.class);
            return OperationResult.buildDefaultSuccess();
        } catch (Exception e) {
            return OperationResult.buildFail("笔记本更新失败");
        }
    }

    @Override
    public Note findById(String id) {
        return noteDao.findById(id).orElse(new Note());
    }

    @Override
    public List<Note> findAll(String userId) {
        Note note = new Note();
        note.setUserId(userId);
        return noteDao.findAll(Example.of(note));
    }

    @Override
    public Page<Note> findPage(Note note, int page, int size) {
        Criteria criteria = new Criteria();
        Query query = new Query();
        query.skip(page*size).limit(size);
        if(!StringUtils.isEmpty(note.getUserId())) {
            query.addCriteria(Criteria.where("userId").is(note.getUserId()));
        }
        if(!StringUtils.isEmpty(note.getName())) {
            query.addCriteria(Criteria.where("name").alike(Example.of(note.getName())));
        }
        long count = mongoTemplate.count(query, Note.class);
        List<Note> notes = mongoTemplate.find(query, Note.class);
        return new PageImpl<Note>(notes,PageRequest.of(page,size),count);
    }
}
