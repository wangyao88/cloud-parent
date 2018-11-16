package com.sxkl.project.cloudnote.note.dao;

import com.sxkl.project.cloudnote.note.entity.Note;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface NoteDao extends MongoRepository<Note,String> {
}
