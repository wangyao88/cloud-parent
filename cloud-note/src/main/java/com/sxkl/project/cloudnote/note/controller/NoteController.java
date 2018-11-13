package com.sxkl.project.cloudnote.note.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/note")
public class NoteController {

    @GetMapping("/test")
    public String test() {
        return "note";
    }
}
