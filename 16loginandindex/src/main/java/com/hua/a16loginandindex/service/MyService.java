package com.hua.a16loginandindex.service;

import com.hua.a16loginandindex.bean.AccessBean;

import java.util.HashMap;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

public interface MyService {
    @POST("oauth/token")
    Observable<AccessBean> loginOb(@QueryMap HashMap<String, Object> data);
}
