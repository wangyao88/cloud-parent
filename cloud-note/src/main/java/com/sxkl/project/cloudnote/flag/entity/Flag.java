package com.sxkl.project.cloudnote.flag.entity;

import com.google.common.collect.Lists;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Data
@Document(collection = "flag")
public class Flag {

    @Id
    private String id;
    private String name;
    private String parentId;
    @Transient
    private List<Flag> children = Lists.newArrayList();
    private Date createDate;
    private String noteId;
    private String userId;

    public static final String ROOT_PARENT_ID = "0";

    public Flag() {}

    public Flag(String name) {
        this.name = name;
    }

    public static Flag getRootFlag() {
        Flag flag = new Flag();
        flag.setId(ROOT_PARENT_ID);
        return flag;
    }
}
