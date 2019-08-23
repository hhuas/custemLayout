package com.hua.a16loginandindex.ui.index;

import android.content.Intent;
import android.os.Bundle;

import com.hua.a16loginandindex.BR;
import com.hua.a16loginandindex.R;
import com.hua.a16loginandindex.bean.AccessBean;
import com.hua.a16loginandindex.databinding.ActivityIndexBinding;

import me.goldze.mvvmhabit.base.BaseActivity;

public class IndexActivity extends BaseActivity<ActivityIndexBinding, IndexViewModel> {
    @Override
    public int initContentView(Bundle bundle) {
        return R.layout.activity_index;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initData() {
        super.initData();
        viewModel.smartRefreshOB.set(binding.indexSmartLayout);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        viewModel.accessBeanOB.set((AccessBean) extras.getParcelable("access"));

        viewModel.initSmart();
        viewModel.initData();
    }
}
