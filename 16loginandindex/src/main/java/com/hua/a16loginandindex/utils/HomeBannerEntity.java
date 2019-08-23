package com.hua.a16loginandindex.utils;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Copyright (辽ICP备19003371号), 2017-2019, 遇尚艾网络科技有限公司
 * FileName: HomeBannerEntity
 * Author:   花恩成
 * Date:     2019/4/12 13:48
 * Description: 首页轮播图实体类
 */
public class HomeBannerEntity implements Parcelable {
    private int id;
    private String thumb;
    private String name;
    private int type;
    private int to_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    public HomeBannerEntity() {
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getTo_id() {
        return to_id;
    }

    public void setTo_id(int to_id) {
        this.to_id = to_id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.thumb);
        dest.writeString(this.name);
        dest.writeInt(this.type);
        dest.writeInt(this.to_id);
    }

    protected HomeBannerEntity(Parcel in) {
        this.id = in.readInt();
        this.thumb = in.readString();
        this.name = in.readString();
        this.type = in.readInt();
        this.to_id = in.readInt();
    }

    public static final Creator<HomeBannerEntity> CREATOR = new Creator<HomeBannerEntity>() {
        @Override
        public HomeBannerEntity createFromParcel(Parcel source) {
            return new HomeBannerEntity(source);
        }

        @Override
        public HomeBannerEntity[] newArray(int size) {
            return new HomeBannerEntity[size];
        }
    };
}
