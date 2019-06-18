package com.hua.a09mvvmmaster312.entity;

public class MyBaseResponce<T> {
    private int code;
    private String message;
    private T datas;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getDatas() {
        return datas;
    }

    public void setDatas(T datas) {
        this.datas = datas;
    }

    public boolean isOk() {
        return code == 0;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
