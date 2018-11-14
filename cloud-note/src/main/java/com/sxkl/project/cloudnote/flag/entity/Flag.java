package com.sxkl.project.cloudnote.flag.entity;

import com.google.common.collect.Lists;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document(collection = "flag")
public class Flag {

    @Id
    private String id;
    private String name;
    private Flag parent;
    private List<Flag> children = Lists.newArrayList();
    private String noteId;
    private String userId;
}
