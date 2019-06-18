package com.hua.a09mvvmmaster312.entity;

import android.os.Parcel;
import android.os.Parcelable;

public class CatagoryEntity implements Parcelable {
    private int id;
    private String category_name;
    private int parent_id;
    private int type;
    private String thumb;
    private int level;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public int getParent_id() {
        return parent_id;
    }

    public void setParent_id(int parent_id) {
        this.parent_id = parent_id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.category_name);
        dest.writeInt(this.parent_id);
        dest.writeInt(this.type);
        dest.writeString(this.thumb);
        dest.writeInt(this.level);
    }

    public CatagoryEntity() {
    }

    protected CatagoryEntity(Parcel in) {
        this.id = in.readInt();
        this.category_name = in.readString();
        this.parent_id = in.readInt();
        this.type = in.readInt();
        this.thumb = in.readString();
        this.level = in.readInt();
    }

    public static final Parcelable.Creator<CatagoryEntity> CREATOR = new Parcelable.Creator<CatagoryEntity>() {
        @Override
        public CatagoryEntity createFromParcel(Parcel source) {
            return new CatagoryEntity(source);
        }

        @Override
        public CatagoryEntity[] newArray(int size) {
            return new CatagoryEntity[size];
        }
    };
}
