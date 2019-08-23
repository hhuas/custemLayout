package com.hua.a17mvptest01.contract;

import com.hua.a17mvptest01.base.BaseView;
import com.hua.a17mvptest01.bean.AccessBean;

import io.reactivex.Flowable;

public interface MainContract {

    interface Model{
       Flowable<AccessBean> login(String username, String password);
    }

    interface View extends BaseView{
        @Override
        void showLoading();

        @Override
        void hideLoading();

        @Override
        void onError(Throwable throwable);

        void onLoginSuccess(AccessBean accessBean);
    }

    interface Presenter{
        void login(String username, String password);
    }
}
