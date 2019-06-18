package com.hua.a09mvvmmaster312.app;

import com.hua.a09mvvmmaster312.data.MyDataRepository;
import com.hua.a09mvvmmaster312.data.source.HttpDataSource;
import com.hua.a09mvvmmaster312.data.source.LocalDataSource;
import com.hua.a09mvvmmaster312.data.source.http.HttpDataSourceImpl;
import com.hua.a09mvvmmaster312.data.source.http.service.MyApiService;
import com.hua.a09mvvmmaster312.data.source.local.LocalDataSourceImpl;
import com.hua.a09mvvmmaster312.utils.RetrofitClient;

public class Injection {

    public static MyDataRepository provideMyDataRepository() {
        MyApiService myApiService = RetrofitClient.getInstance().create(MyApiService.class);
        HttpDataSource mHttpDataSource = HttpDataSourceImpl.getInstance(myApiService);
        LocalDataSource mLocalDataSource = LocalDataSourceImpl.getInstance();

        return MyDataRepository.getInstance(mHttpDataSource, mLocalDataSource);
    }
}
