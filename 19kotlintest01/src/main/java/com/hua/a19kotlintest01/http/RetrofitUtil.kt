package com.hua.a19kotlintest01.http

import com.hua.a19kotlintest01.service.RetrofitService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitUtil {

    companion object {

        val baseUrl: String = "http://v.juhe.cn/"
        /**
         * 创建retrofit
         */
        fun create(): Retrofit {
            //日志显示级别
            val level: HttpLoggingInterceptor.Level = HttpLoggingInterceptor.Level.BODY
            //新建log拦截器
            val loggingInterceptor: HttpLoggingInterceptor = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger { message ->
                HttpLoggingInterceptor.Logger.DEFAULT
            })
            loggingInterceptor.level = level;
            //okHttpClientBuilder
            val okHttpClientBuilder = OkHttpClient().newBuilder()

            okHttpClientBuilder.connectTimeout(60, TimeUnit.SECONDS)
                    .readTimeout(60, TimeUnit.SECONDS)
                    //OkHttp进行添加拦截器loggingInterceptor
                    .addInterceptor(loggingInterceptor)
            return Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .client(okHttpClientBuilder.build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build()
        }

        val retrofitService: RetrofitService = getService(RetrofitService::class.java)

        fun <T> getService(service: Class<T>): T {
            return RetrofitUtil.create().create(service);
        }
    }
}