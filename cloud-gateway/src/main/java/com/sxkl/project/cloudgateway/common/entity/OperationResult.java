package com.sxkl.project.cloudgateway.common.entity;

public final class OperationResult {

    private String msg;
    private Boolean status;
    private Object data;
    private Throwable cause;

    private static final String DEFAULT_SUCCESS_MSG = "操作成功";
    private static final String DEFAULT_FAIL_MSG = "操作失败";
    private static final Boolean DEFAULT_SUCCESS_STATUS = Boolean.TRUE;
    private static final Boolean DEFAULT_FAIL_STATUS = Boolean.FALSE;

    private OperationResult() {}

    public static OperationResult buildDefaultSuccess() {
        return buildSuccess(DEFAULT_SUCCESS_MSG,null);
    }

    public static OperationResult buildSuccess(String msg) {
        return buildSuccess(msg,null);
    }

    public static OperationResult buildSuccess(String msg, Object data) {
        OperationResult result = new OperationResult();
        result.setMsg(msg);
        result.setStatus(DEFAULT_SUCCESS_STATUS);
        result.setData(data);
        return result;
    }

    public static OperationResult buildDefaultFail() {
        return buildFail(DEFAULT_FAIL_MSG,null,null);
    }

    public static OperationResult buildFail(String msg) {
        return buildFail(msg,null);
    }

    public static OperationResult buildFail(String msg, Object data) {
        return buildFail(msg,data,null);
    }

    public static OperationResult buildFail(Throwable cause) {
        return buildFail(cause.getMessage(),null,cause);
    }

    public static OperationResult buildFail(String mag, Throwable cause) {
        return buildFail(mag,null,cause);
    }

    public static OperationResult buildFail(String msg, Object data, Throwable cause) {
        OperationResult result = new OperationResult();
        result.setMsg(msg);
        result.setStatus(DEFAULT_FAIL_STATUS);
        result.setData(data);
        result.setCause(cause);
        return result;
    }

    public String getMsg() {
        return msg;
    }

    private void setMsg(String msg) {
        this.msg = msg;
    }

    public Boolean getStatus() {
        return status;
    }

    private void setStatus(Boolean status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    private void setData(Object data) {
        this.data = data;
    }

    public Throwable getCause() {
        return cause;
    }

    private void setCause(Throwable cause) {
        this.cause = cause;
    }
}
