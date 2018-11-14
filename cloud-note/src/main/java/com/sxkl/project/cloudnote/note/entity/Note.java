package com.sxkl.project.cloudnote.note.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "note")
public class Note {

    @Id
    private String id;
    private String name;
    private String userId;

    public Note() {}

    public Note(String name) {
        this.name = name;
    }
}
