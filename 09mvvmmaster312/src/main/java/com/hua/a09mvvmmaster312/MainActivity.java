package com.hua.a09mvvmmaster312;

import android.arch.lifecycle.ViewModelProviders;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.hua.a09mvvmmaster312.app.AppViewModelFactory;
import com.hua.a09mvvmmaster312.databinding.ActivityMainBinding;
import com.hua.a09mvvmmaster312.viewmodel.MainViewModel;

import me.goldze.mvvmhabit.base.BaseActivity;

public class MainActivity extends BaseActivity<ActivityMainBinding, MainViewModel> {

    @Override
    public void initParam() {
        super.initParam();
    }

    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_main;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public MainViewModel initViewModel() {
        AppViewModelFactory factory = AppViewModelFactory.getInstance(getApplication());
        return ViewModelProviders.of(this, factory).get(MainViewModel.class);
    }

    @Override
    public void initData() {
        super.initData();
        viewModel.initTest();
    }
}
