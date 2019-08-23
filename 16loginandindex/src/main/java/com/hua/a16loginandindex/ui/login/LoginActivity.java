package com.hua.a16loginandindex.ui.login;

import android.os.Bundle;

import com.hua.a16loginandindex.BR;
import com.hua.a16loginandindex.R;
import com.hua.a16loginandindex.databinding.ActivityLoginBinding;

import me.goldze.mvvmhabit.base.BaseActivity;

public class LoginActivity extends BaseActivity<ActivityLoginBinding,LoginViewModel> {
    @Override
    public int initContentView(Bundle bundle) {
        return R.layout.activity_login;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }
}
