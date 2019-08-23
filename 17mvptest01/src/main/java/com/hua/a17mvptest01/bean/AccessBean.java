package com.hua.a17mvptest01.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class AccessBean implements Parcelable {

    private String access_token;
    private String token_type;
    private String refresh_token;
    private int expires_in;
    private String scope;
    private String jti;

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getToken_type() {
        return token_type;
    }

    public void setToken_type(String token_type) {
        this.token_type = token_type;
    }

    public String getRefresh_token() {
        return refresh_token;
    }

    public void setRefresh_token(String refresh_token) {
        this.refresh_token = refresh_token;
    }

    public int getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(int expires_in) {
        this.expires_in = expires_in;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getJti() {
        return jti;
    }

    public void setJti(String jti) {
        this.jti = jti;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.access_token);
        dest.writeString(this.token_type);
        dest.writeString(this.refresh_token);
        dest.writeInt(this.expires_in);
        dest.writeString(this.scope);
        dest.writeString(this.jti);
    }

    public AccessBean() {
    }

    protected AccessBean(Parcel in) {
        this.access_token = in.readString();
        this.token_type = in.readString();
        this.refresh_token = in.readString();
        this.expires_in = in.readInt();
        this.scope = in.readString();
        this.jti = in.readString();
    }

    public static final Creator<AccessBean> CREATOR = new Creator<AccessBean>() {
        @Override
        public AccessBean createFromParcel(Parcel source) {
            return new AccessBean(source);
        }

        @Override
        public AccessBean[] newArray(int size) {
            return new AccessBean[size];
        }
    };
}
