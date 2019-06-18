package com.hua.mvvmhabittest01.service;

import io.reactivex.Observable;
import android.databinding.ObservableField;

import com.hua.mvvmhabittest01.entity.MyBaseResponse;

import java.util.Map;

import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface MyApiService {

    @POST("/api/retrofit2/a1?type=1")
    Observable<MyBaseResponse> testRetrofit2(@Query("type") Map s);

//    @GET("/api/Ad/index")
//    Observable<MyBaseResponse> testRetrofit2();
}
