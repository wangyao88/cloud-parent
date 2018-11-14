package com.sxkl.project.cloudnote.job;

import com.sxkl.project.cloudnote.common.SpringBeanManager;
import lombok.Data;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.Date;
import java.util.UUID;

@Data
public class UpdateArticleByDeleteNoteJob extends BaseJob {

    public UpdateArticleByDeleteNoteJob(){}

    public UpdateArticleByDeleteNoteJob(String noteId) {
        UpdateArticleByDeleteNoteJob job = new UpdateArticleByDeleteNoteJob();
        job.setId(UUID.randomUUID().toString());
        job.setName("删除笔记本关联的笔记"+noteId);
        job.setData(noteId);
        job.setCreateDate(new Date());
    }

    @Override
    public void doJob() {
        MongoTemplate mongoTemplate = SpringBeanManager.getMongoTemplate();
        String noteId = this.getData().toString();

    }
}
