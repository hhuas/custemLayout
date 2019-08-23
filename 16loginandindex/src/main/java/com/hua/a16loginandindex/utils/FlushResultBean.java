package com.hua.a16loginandindex.utils;

import android.os.Parcel;
import android.os.Parcelable;

public class FlushResultBean implements Parcelable {
    private int code;
    private String message;

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


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.code);
        dest.writeString(this.message);
    }

    public FlushResultBean() {
    }

    protected FlushResultBean(Parcel in) {
        this.code = in.readInt();
        this.message = in.readString();
    }

    public static final Creator<FlushResultBean> CREATOR = new Creator<FlushResultBean>() {
        @Override
        public FlushResultBean createFromParcel(Parcel source) {
            return new FlushResultBean(source);
        }

        @Override
        public FlushResultBean[] newArray(int size) {
            return new FlushResultBean[size];
        }
    };
}
