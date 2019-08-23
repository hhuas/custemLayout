package com.hua.a17mvptest01.presenter;

import android.util.Log;

import com.hua.a17mvptest01.base.BasePresenter;
import com.hua.a17mvptest01.bean.AccessBean;
import com.hua.a17mvptest01.contract.MainContract;
import com.hua.a17mvptest01.model.MainModel;
import com.hua.a17mvptest01.net.RxScheduler;

import io.reactivex.functions.Consumer;

public class MainPresenter extends BasePresenter<MainContract.View> implements MainContract.Presenter {

    private MainContract.Model model;

    public MainPresenter() {
        model = new MainModel();
    }

    @Override
    public void login(String username, String password) {
        if (!isViewAttached()) {
            return;
        }
        mView.showLoading();
        model.login(username, password)
                .compose(RxScheduler.<AccessBean>Flo_io_main())
                .as(mView.<AccessBean>bindAutoDispose())
                .subscribe(new Consumer<AccessBean>() {
                               @Override
                               public void accept(AccessBean s) throws Exception {
                                   mView.hideLoading();
                                   mView.onLoginSuccess(s);
                                   Log.e("TAG", s.getAccess_token());
                               }
                           }
                        , new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {
                                mView.hideLoading();
                            }
                        });
    }
}
