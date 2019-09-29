package com.hua.a19kotlintest01.service

import com.hua.a19kotlintest01.bean.CalentarDayBean
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitService {

    /**
     *  获取当天详细信息
     *  @param date 日期
     */
    @GET("calendar/day")
    fun calenderDay(
            @Query("date") date: String,
            @Query("key") key: String
    ): Observable<CalentarDayBean>

    /**
     *  获取近期假期
     *  @param date 日期
     */
    @GET("calendar/month")
    fun calenderMonth(
            @Query("date") date: String
    ): Observable<CalentarDayBean>

    /**
     *  获取当年假期列表
     *  @param date 日期
     */
    @GET("calendar/year")
    fun calenderYear(
            @Query("date") date: String
    ): Observable<CalentarDayBean>
}