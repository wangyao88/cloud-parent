package com.sxkl.project.cloudnote.job;

import com.google.common.collect.Lists;

import java.util.List;

public class JobManager {

    private static List<BaseJob> jobs = Lists.newCopyOnWriteArrayList();

    private JobManager(){}

    private static class Singleton {
        private static final JobManager JOB_MANAGER = new JobManager();
    }

    public static void startUpdateArticleByDeleteNoteJob(String noteId) {
        jobs.add(new UpdateArticleByDeleteNoteJob(noteId));
    }


}
