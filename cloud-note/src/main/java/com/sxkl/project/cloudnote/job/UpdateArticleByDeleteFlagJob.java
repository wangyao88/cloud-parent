package com.sxkl.project.cloudnote.job;

import com.sxkl.project.cloudnote.common.SpringBeanManager;
import lombok.Data;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.Date;
import java.util.UUID;

@Data
public class UpdateArticleByDeleteFlagJob extends BaseJob {

    public UpdateArticleByDeleteFlagJob(){}

    public UpdateArticleByDeleteFlagJob(String noteId) {
        UpdateArticleByDeleteFlagJob job = new UpdateArticleByDeleteFlagJob();
        job.setId(UUID.randomUUID().toString());
        job.setName("更新标签关联的笔记"+noteId);
        job.setData(noteId);
        job.setCreateDate(new Date());
    }

    @Override
    public void doJob() {
        MongoTemplate mongoTemplate = SpringBeanManager.getMongoTemplate();
        String noteId = this.getData().toString();

    }
}
