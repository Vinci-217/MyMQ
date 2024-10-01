package com.mq.cn.messagequeue.entity;

public class Result {
    private String code;
    private String message;
    private Object data;

    public Result(String code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static Result success() {
        return new Result("200", "success", null);
    }
}
