package com.hua.a17mvptest01.model;

import com.hua.a17mvptest01.bean.AccessBean;
import com.hua.a17mvptest01.contract.MainContract;
import com.hua.a17mvptest01.net.APIService;
import com.hua.a17mvptest01.net.RetrofitClient;

import io.reactivex.Flowable;

public class MainModel implements MainContract.Model {
    public APIService apiService;

    public MainModel() {
        if (apiService == null) {
            apiService = RetrofitClient.getInstance().getApi();
        }
    }

    @Override
    public Flowable<AccessBean> login(String username, String password) {
        return apiService.login(username, password, "password");
    }
}
