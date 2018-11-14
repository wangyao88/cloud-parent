package com.sxkl.project.cloudnote.note.controller;

import com.sxkl.project.cloudnote.common.OperationResult;
import com.sxkl.project.cloudnote.note.entity.Note;
import com.sxkl.project.cloudnote.note.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/note")
public class NoteController {

    @Autowired
    private NoteService noteService;

    @PostMapping("/save")
    public OperationResult save(@RequestBody Note note) {
        return noteService.save(note);
    }

    @PostMapping("/deleteById")
    public OperationResult deleteById(String noteId) {
        return noteService.deleteById(noteId);
    }

    @PostMapping("/forceDeleteById")
    public OperationResult forceDeleteById(String noteId) {
        return noteService.forceDeleteById(noteId);
    }

    @PostMapping("/update")
    public OperationResult update(@RequestBody Note note) {
        return noteService.update(note);
    }

    @GetMapping("/findById")
    public Note findById(String noteId) {
        return noteService.findById(noteId);
    }

    @GetMapping("/findAll")
    public List<Note> findAll(String noteId) {
        return noteService.findAll();
    }

    @GetMapping("/findPage")
    public Page<Note> findPage(int page, int size) {
        return noteService.findPage(page,size);
    }
}
