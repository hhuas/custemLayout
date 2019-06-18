package com.hua.a09mvvmmaster312.data.source.http.service;

import com.hua.a09mvvmmaster312.entity.CatagoryEntity;
import com.hua.a09mvvmmaster312.entity.MyBaseResponce;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MyApiService {

    @GET("api/Category/index")
    Observable<MyBaseResponce<List<CatagoryEntity>>> getTestRepository(@Query("level") String level, @Query("type") String type);
}
