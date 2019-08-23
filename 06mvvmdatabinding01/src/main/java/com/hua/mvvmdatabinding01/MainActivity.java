package com.hua.mvvmdatabinding01;

import android.databinding.DataBindingUtil;
import android.databinding.ObservableField;
import android.databinding.ViewDataBinding;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.hua.mvvmdatabinding01.databinding.ActivityMainBinding;
import com.hua.mvvmdatabinding01.model.User;
import com.hua.mvvmdatabinding01.model.User2;
import com.hua.mvvmdatabinding01.utils.ResponseThrowable;
import com.hua.mvvmdatabinding01.utils.RetrofitClient;
import com.hua.mvvmdatabinding01.utils.RxUtils;
import com.trello.rxlifecycle2.LifecycleProvider;

import java.util.Random;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

public class MainActivity extends AppCompatActivity {
    private User user;
    private ActivityMainBinding binding;
    private User2 user2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
//        setContentView(R.layout.activity_main);
        user = new User("小花先生", "123456");
//        binding.setVariable(BR.userInfo,user);
        binding.setUserInfo(user);

        user2 = new User2("嘿嘿嘿", "2222222222");
        binding.setUser2(user2);
    }


    public void testObUser(View view) {
        user2.name.set("嘿嘿嘿33");
        user2.password.set("333333"+ Math.random()*100);

        RetrofitClient.getInstance(this).create(MyService.class)
                .checkFlushState2("0")
//                .compose(RxUtils.bindToLifecycle(LifecycleProvider) //请求与View周期同步
                .compose(RxUtils.schedulersTransformer()) //线程调度
                .compose(RxUtils.exceptionTransformer()) // 网络错误的异常转换, 这里可以换成自己的ExceptionHandle
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
//                        showDialog("正在请求...");
                        disposable.dispose();
                    }
                }).subscribe(new Consumer<String>() {

            @Override
            public void accept(String s) throws Exception {
                Log.e("TAG",s);
            }
        }, new Consumer<ResponseThrowable>() {
            @Override
            public void accept(ResponseThrowable throwable) throws Exception {
                //关闭对话框
                throwable.printStackTrace();
            }
        }, new Action() {
            @Override
            public void run() throws Exception {
                //关闭对话框
            }
        });
    }
}
