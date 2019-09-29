package com.lianxin.a21dbflow01.db.data;

import com.lianxin.a21dbflow01.db.PlanetPlanDB;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

/**
 * FileName: UserData
 * Author:   花恩成
 * Date:     2019/9/27 16:22
 * Description:
 */
@Table(database = PlanetPlanDB.class)
public class UserData extends BaseModel {

    @Column
    @PrimaryKey
    private int id;
    @Column
    private String name;
    @Column
    private int age;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }


}
