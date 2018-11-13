package com.sxkl.project.cloudnote.note.service;

import com.sxkl.project.cloudnote.article.service.ArticeService;
import com.sxkl.project.cloudnote.common.OperationResult;
import com.sxkl.project.cloudnote.note.dao.NoteDao;
import com.sxkl.project.cloudnote.note.entity.Note;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteServiceImpl implements NoteService {

    @Autowired
    private NoteDao noteDao;
    @Autowired
    private ArticeService articeService;

    @Override
    public OperationResult save(Note note) {
        try {
            noteDao.save(note);
            return OperationResult.buildDefaultSuccess();
        } catch (Exception e) {
            return OperationResult.buildFail("笔记本保存失败");
        }
    }

    @Override
    public OperationResult delete(String id) {
        try {
            noteDao.deleteById(id);
            return OperationResult.buildDefaultSuccess();
        } catch (Exception e) {
            return OperationResult.buildFail("笔记本保存失败");
        }
    }

    @Override
    public OperationResult forceDelete(String id) {


        return null;
    }

    @Override
    public OperationResult update(Note note) {
        return null;
    }

    @Override
    public Note findById(String id) {
        return null;
    }

    @Override
    public List<Note> findAll() {
        return null;
    }

    @Override
    public Page<Note> findPage(int page, int size) {
        return null;
    }
}
