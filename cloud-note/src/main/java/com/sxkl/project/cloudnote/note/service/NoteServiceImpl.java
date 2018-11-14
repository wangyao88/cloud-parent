package com.sxkl.project.cloudnote.note.service;

import com.sxkl.project.cloudnote.article.service.ArticeService;
import com.sxkl.project.cloudnote.common.OperationResult;
import com.sxkl.project.cloudnote.job.JobManager;
import com.sxkl.project.cloudnote.note.dao.NoteDao;
import com.sxkl.project.cloudnote.note.entity.Note;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
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
    private ArticeService articeService;
    @Autowired
    private MongoTemplate mongoTemplate;

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
        return mongoTemplate.findById(id,Note.class);
    }

    @Override
    public List<Note> findAll() {
        return mongoTemplate.findAll(Note.class);
    }

    @Override
    public Page<Note> findPage(int page, int size) {
        return noteDao.findAll(PageRequest.of(page,size));
    }
}
