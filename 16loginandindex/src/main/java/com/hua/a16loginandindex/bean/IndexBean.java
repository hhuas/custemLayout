package com.hua.a16loginandindex.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class IndexBean implements Parcelable {
    private int position;

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.position);
    }

    public IndexBean() {
    }

    protected IndexBean(Parcel in) {
        this.position = in.readInt();
    }

    public static final Parcelable.Creator<IndexBean> CREATOR = new Parcelable.Creator<IndexBean>() {
        @Override
        public IndexBean createFromParcel(Parcel source) {
            return new IndexBean(source);
        }

        @Override
        public IndexBean[] newArray(int size) {
            return new IndexBean[size];
        }
    };
}
