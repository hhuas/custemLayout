package com.hua.mvvmhabittest01.ui.viewModel;

import android.app.Application;
import android.nfc.Tag;
import android.support.annotation.NonNull;
import android.util.Log;

import com.hua.mvvmhabittest01.entity.MyBaseResponse;
import com.hua.mvvmhabittest01.service.MyApiService;
import com.hua.mvvmhabittest01.utils.RetrofitClient;

import java.util.HashMap;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import me.goldze.mvvmhabit.base.BaseViewModel;
import me.goldze.mvvmhabit.http.BaseResponse;
import me.goldze.mvvmhabit.http.ResponseThrowable;
import me.goldze.mvvmhabit.utils.RxUtils;
import me.goldze.mvvmhabit.utils.ToastUtils;

public class Retrofit2ViewModel extends BaseViewModel {
    private static final String TAG = Retrofit2ViewModel.class.getSimpleName();

    public Retrofit2ViewModel(@NonNull Application application) {
        super(application);
    }

    public void initRetrofit2() {
        HashMap<Object, Object> map = new HashMap<>();
        map.put("string","string");
        map.put("age",11);
        map.put("length",22);


        RetrofitClient.getInstance().create(MyApiService.class)
                .testRetrofit2(map)
                .compose(RxUtils.bindToLifecycle(getLifecycleProvider())) //请求与View周期同步
                .compose(RxUtils.schedulersTransformer()) //线程调度
                .compose(RxUtils.exceptionTransformer()) // 网络错误的异常转换, 这里可以换成自己的ExceptionHandle
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        showDialog("正在请求...");
                    }
                })
                .subscribe(new Consumer<MyBaseResponse>() {

                    @Override
                    public void accept(MyBaseResponse myBaseResponse) throws Exception {
                        Log.e(TAG, "请求的数据code：" + myBaseResponse.getCode());
                    }
                }, new Consumer<ResponseThrowable>() {
                    @Override
                    public void accept(ResponseThrowable throwable) throws Exception {
                        //关闭对话框
                        dismissDialog();
                        //请求刷新完成收回
                        Log.e(TAG, throwable.toString());
                        ToastUtils.showShort(throwable.message);
                        throwable.printStackTrace();
                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {
                        //关闭对话框
                        dismissDialog();
                        //请求刷新完成收回
                    }
                });
    }
}
