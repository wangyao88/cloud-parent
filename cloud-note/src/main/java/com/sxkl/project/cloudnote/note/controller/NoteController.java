package com.sxkl.project.cloudnote.note.controller;

import com.sxkl.project.cloudnote.common.OperationResult;
import com.sxkl.project.cloudnote.note.entity.Note;
import com.sxkl.project.cloudnote.note.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class NoteController{

    @Autowired
    private NoteService noteService;

    @PostMapping("/noteSave")
    public OperationResult save(@RequestBody Note note) {
        return noteService.save(note);
    }

    @PostMapping("/noteDeleteById")
    public OperationResult deleteById(String noteId) {
        return noteService.deleteById(noteId);
    }

    @PostMapping("/noteForceDeleteById")
    public OperationResult forceDeleteById(String noteId) {
        return noteService.forceDeleteById(noteId);
    }

    @PostMapping("/noteUpdate")
    public OperationResult update(@RequestBody Note note) {
        return noteService.update(note);
    }

    @GetMapping("/noteFindById")
    public Note findById(String noteId) {
        return noteService.findById(noteId);
    }

    @GetMapping("/noteFindAll")
    public List<Note> findAll(String userId) {
        return noteService.findAll(userId);
    }

    @GetMapping("/noteFindPage")
    public Page<Note> findPage(Note note, int page, int size) {
        return noteService.findPage(note,page,size);
    }
}
