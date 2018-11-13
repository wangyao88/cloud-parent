package com.sxkl.project.cloudnote.article.entity;

import com.google.common.collect.Lists;
import com.sxkl.project.cloudnote.flag.entity.Flag;
import com.sxkl.project.cloudnote.note.entity.Note;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Data
@Document(collection = "article")
public class Aticle {

    @Id
    private ObjectId id;
    private String tile;
    private List<Note> notes = Lists.newArrayList();
    private List<Flag> flags = Lists.newArrayList();
    private String shotDescription;
    private String content;
    private Date createDate;
    private Date lastUpdateDate;
    private Boolean shared = Boolean.FALSE;
    private Boolean favourite = Boolean.FALSE;
    private ObjectId userId;
}
