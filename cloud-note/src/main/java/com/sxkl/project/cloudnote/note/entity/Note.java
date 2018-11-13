package com.sxkl.project.cloudnote.note.entity;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "note")
public class Note {

    @Id
    private ObjectId id;
    private String name;
    private ObjectId userId;
}
