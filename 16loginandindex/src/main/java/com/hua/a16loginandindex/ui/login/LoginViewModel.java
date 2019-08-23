package com.hua.a16loginandindex.ui.login;

import android.app.Application;
import android.databinding.ObservableField;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;

import com.hua.a16loginandindex.bean.AccessBean;
import com.hua.a16loginandindex.service.MyService;
import com.hua.a16loginandindex.ui.index.IndexActivity;
import com.hua.a16loginandindex.utils.RetrofitClient;

import java.util.HashMap;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import me.goldze.mvvmhabit.base.BaseViewModel;
import me.goldze.mvvmhabit.binding.command.BindingAction;
import me.goldze.mvvmhabit.binding.command.BindingCommand;
import me.goldze.mvvmhabit.http.ResponseThrowable;
import me.goldze.mvvmhabit.utils.RxUtils;

public class LoginViewModel extends BaseViewModel {
    public LoginViewModel(@NonNull Application application) {
        super(application);
    }

    public ObservableField<String> nameTextOb = new ObservableField<>("");
    public ObservableField<String> pwdTextOb = new ObservableField<>("");
    private HashMap loginData = new HashMap();


    public BindingCommand loginClick = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            Log.e("TAG", "click");
            Log.e("TAG", nameTextOb.get());
            Log.e("TAG", pwdTextOb.get());

            loginData.put("grant_type", "password");
            loginData.put("username", nameTextOb.get());
            loginData.put("password", pwdTextOb.get());

            RetrofitClient.getInstance().create(MyService.class)
                    .loginOb(loginData)
                    .compose(RxUtils.bindToLifecycle(getLifecycleProvider())) //请求与View周期同步
                    .compose(RxUtils.schedulersTransformer()) //线程调度
                    .compose(RxUtils.exceptionTransformer()) // 网络错误的异常转换, 这里可以换成自己的ExceptionHandle
                    .doOnSubscribe(new Consumer<Disposable>() {
                        @Override
                        public void accept(Disposable disposable) throws Exception {
//                        showDialog("正在请求...");
                        }
                    }).subscribe(new Consumer<AccessBean>() {
                @Override
                public void accept(AccessBean response) throws Exception {
                    Bundle bundle = new Bundle();
                    String access_token = response.getAccess_token();
                    Log.e("TAG", "access_token:" + access_token);
                    bundle.putParcelable("access", response);
                    startActivity(IndexActivity.class, bundle);
                }
            }, new Consumer<ResponseThrowable>() {
                @Override
                public void accept(ResponseThrowable throwable) throws Exception {
                    //关闭对话框
                    dismissDialog();
                    throwable.printStackTrace();
                }
            }, new Action() {
                @Override
                public void run() throws Exception {
                    //关闭对话框
                    dismissDialog();
                }
            });


        }
    });

    public void initDispose() {
        MyService myService = RetrofitClient.getInstance().create(MyService.class);
        Observable compose = myService.loginOb(loginData).compose(RxUtils.bindToLifecycle(getLifecycleProvider())) //请求与View周期同步
                .compose(RxUtils.schedulersTransformer()) //线程调度
                .compose(RxUtils.exceptionTransformer());// 网络错误的异常转换, 这里可以换成自己的ExceptionHandle

        Observable observable = myService.loginOb(loginData).compose(RxUtils.bindToLifecycle(getLifecycleProvider())) //请求与View周期同步
                .compose(RxUtils.schedulersTransformer()) //线程调度
                .compose(RxUtils.exceptionTransformer())// 网络错误的异常转换, 这里可以换成自己的ExceptionHandle
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {

                    }
                });
        observable.subscribe(new MyConsumer());
    }

    class MyConsumer implements Consumer<AccessBean> {
        @Override
        public void accept(AccessBean accessBean) throws Exception {

        }
    }
}
