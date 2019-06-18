package com.hua.mvvmhabittest01.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.hua.mvvmhabittest01.BR;
import com.hua.mvvmhabittest01.R;
import com.hua.mvvmhabittest01.databinding.ActivityRetrofit2Binding;
import com.hua.mvvmhabittest01.ui.viewModel.Retrofit2ViewModel;

import me.goldze.mvvmhabit.base.BaseActivity;

public class Retrofit2Activity extends BaseActivity<ActivityRetrofit2Binding, Retrofit2ViewModel> {


    @Override
    public int initContentView(Bundle bundle) {
        return R.layout.activity_retrofit2;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initData() {
        super.initData();
        viewModel.initRetrofit2();
    }
}
