package com.hua.mvvmdatabinding01;


import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface MyService {

//    @GET("pos/{deviceId}/window/list")
//    Flowable<ApiResult<List<Stall>>> checkFlushState(@Path("deviceId") String deviceId);

    //FormatUtils.getDeviceID()

    @GET("Ad/index")
    Observable<String> checkFlushState2(@Query("position") String position);
}
