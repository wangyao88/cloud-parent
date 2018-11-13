package com.sxkl.project.cloudgateway.common.entity;

import reactor.core.publisher.Mono;

public final class ReactiveOperationResult {

    private String msg;
    private Boolean status;
    private Object data;
    private Throwable cause;

    private static final String DEFAULT_SUCCESS_MSG = "操作成功";
    private static final String DEFAULT_FAIL_MSG = "操作失败";
    private static final Boolean DEFAULT_SUCCESS_STATUS = Boolean.TRUE;
    private static final Boolean DEFAULT_FAIL_STATUS = Boolean.FALSE;

    private ReactiveOperationResult() {}

    public static Mono<ReactiveOperationResult> buildDefaultSuccess() {
        return buildSuccess(DEFAULT_SUCCESS_MSG,null);
    }

    public static Mono<ReactiveOperationResult> buildSuccess(String msg) {
        return buildSuccess(msg,null);
    }

    public static Mono<ReactiveOperationResult> buildSuccess(String msg, Object data) {
        ReactiveOperationResult result = new ReactiveOperationResult();
        result.setMsg(msg);
        result.setStatus(DEFAULT_SUCCESS_STATUS);
        result.setData(data);
        return Mono.just(result);
    }

    public static Mono<ReactiveOperationResult> buildDefaultFail() {
        return buildFail(DEFAULT_FAIL_MSG,null,null);
    }

    public static Mono<ReactiveOperationResult> buildFail(String msg) {
        return buildFail(msg,null);
    }

    public static Mono<ReactiveOperationResult> buildFail(String msg, Object data) {
        return buildFail(msg,data,null);
    }

    public static Mono<ReactiveOperationResult> buildFail(Throwable cause) {
        return buildFail(cause.getMessage(),null,cause);
    }

    public static Mono<ReactiveOperationResult> buildFail(String mag, Throwable cause) {
        return buildFail(mag,null,cause);
    }

    public static Mono<ReactiveOperationResult> buildFail(String msg, Object data, Throwable cause) {
        ReactiveOperationResult result = new ReactiveOperationResult();
        result.setMsg(msg);
        result.setStatus(DEFAULT_FAIL_STATUS);
        result.setData(data);
        result.setCause(cause);
        return Mono.just(result);
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
