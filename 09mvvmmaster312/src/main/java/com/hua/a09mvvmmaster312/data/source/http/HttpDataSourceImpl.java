package com.hua.a09mvvmmaster312.data.source.http;

import com.hua.a09mvvmmaster312.data.source.HttpDataSource;
import com.hua.a09mvvmmaster312.data.source.http.service.MyApiService;
import com.hua.a09mvvmmaster312.entity.CatagoryEntity;
import com.hua.a09mvvmmaster312.entity.MyBaseResponce;

import java.util.List;

import io.reactivex.Observable;

public class HttpDataSourceImpl implements HttpDataSource {
    private MyApiService myApiService;

    private volatile static HttpDataSourceImpl INSTANCE = null;

    public static HttpDataSourceImpl getInstance(MyApiService myApiService) {
        if (INSTANCE == null) {
            synchronized (HttpDataSourceImpl.class){
                if(INSTANCE==null){
                    INSTANCE=new HttpDataSourceImpl(myApiService);
                }
            }
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }

    public HttpDataSourceImpl(MyApiService myApiService) {
        this.myApiService = myApiService;
    }

    @Override
    public Observable<MyBaseResponce<List<CatagoryEntity>>> getTestRepository(String level, String type) {
        return myApiService.getTestRepository(level,type);
    }
}
