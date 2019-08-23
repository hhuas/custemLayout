package com.hua.a16loginandindex.utils;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyRetrofitClient {

    private static volatile MyRetrofitClient instance = null;
    private static OkHttpClient okHttpClient;
    private static Retrofit retrofit;

    private static String baseUrl="http://up.yushangai.top/api/";

    public MyRetrofitClient() {
        this(baseUrl, null);
    }

    private static class SingletonHolder {
        private static MyRetrofitClient INSTANCE = new MyRetrofitClient();
    }

    public static MyRetrofitClient getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private MyRetrofitClient(String url, Map<String, Object> headers) {
        okHttpClient = new OkHttpClient.Builder()
                .retryOnConnectionFailure(true)
                .connectTimeout(15, TimeUnit.SECONDS)
                .connectTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                .connectionPool(new ConnectionPool(8, 15, TimeUnit.SECONDS))
                // 这里你可以根据自己的机型设置同时连接的个数和时间，我这里8个，和每个保持时间为10s
                .build();

        retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(url)
                .build();
    }

    public <T> T create(final Class<T> service) {
        if (service == null) {
            throw new RuntimeException("Api service is null!");
        }
        return retrofit.create(service);
    }
}
