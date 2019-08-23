package com.hua.a15java8study01.javastudy.enumtest;

public enum ResultDataEnums {
    SUCCESS(400, "aaaa");

    private int code;
    private String message;

    public int getCode() {
        return code;
    }


    public String getMessage() {
        return message;
    }


    ResultDataEnums(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
