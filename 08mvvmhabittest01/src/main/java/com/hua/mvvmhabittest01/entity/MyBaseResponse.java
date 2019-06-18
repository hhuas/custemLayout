package com.hua.mvvmhabittest01.entity;

public class MyBaseResponse<T> {
    private int code;
    private String message;
    private T datas;

    public MyBaseResponse() {
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getDatas() {
        return datas;
    }

    public void setDatas(T datas) {
        this.datas = datas;
    }

    public boolean isOk() {
        return this.code == 1;
    }

}
