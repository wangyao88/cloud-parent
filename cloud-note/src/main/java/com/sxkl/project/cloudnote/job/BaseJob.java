package com.sxkl.project.cloudnote.job;


import lombok.Data;

import java.util.Date;

@Data
public abstract class BaseJob {

    protected String id;
    protected String name;
    protected Boolean status;
    protected Date createDate;
    protected Date finishDate;
    protected Long costTime;
    protected Object data;

    public abstract void doJob();
}
