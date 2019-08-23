package com.hua.a16loginandindex.utils;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface MyService {

    @GET("flush/card")
    Observable<FlushResultBean> checkFlushState(@Field("card") String card);

    @GET
    Observable<FlushResultBean> checkFlushState2(@Url String url);

    //轮播图
    @GET("Ad/index")
    Observable<MyBaseResponse<List<HomeBannerEntity>>> getHomeBanner(@Query("position") int position);
}
