package com.hua.a10indexview;

public class UnitEntity {
    private String name;
    private String pinyin;


    public UnitEntity(String name) {
        this.name = name;
        this.pinyin = PinYinUtils.getPinYin(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPinyin() {
        return pinyin;
    }
}
