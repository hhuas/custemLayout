package com.hua.app31custem02;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.hua.app31custem02.databinding.ActivitySickCardBinding;

import me.goldze.mvvmhabit.base.BaseActivity;

public class SickCardActivity extends BaseActivity<ActivitySickCardBinding, SickCardViewModel> {

    @Override
    public int initContentView(Bundle bundle) {
        return R.layout.activity_sick_card;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }
}
