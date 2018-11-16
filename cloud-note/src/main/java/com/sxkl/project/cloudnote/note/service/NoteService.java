package com.sxkl.project.cloudnote.note.service;


import com.sxkl.project.cloudnote.common.OperationResult;
import com.sxkl.project.cloudnote.note.entity.Note;
import org.springframework.data.domain.Page;

import java.util.List;

public interface NoteService{

    OperationResult save(Note note);

    OperationResult deleteById(String id);

    OperationResult forceDeleteById(String id);

    OperationResult update(Note note);

    Note findById(String id);

    List<Note> findAll(String userId);

    Page<Note> findPage(Note note, int page, int size);

}
