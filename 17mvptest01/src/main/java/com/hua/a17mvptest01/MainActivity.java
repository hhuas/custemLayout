package com.hua.a17mvptest01;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.hua.a17mvptest01.base.BaseMvpActivity;
import com.hua.a17mvptest01.bean.AccessBean;
import com.hua.a17mvptest01.contract.MainContract;
import com.hua.a17mvptest01.presenter.MainPresenter;
import com.hua.a17mvptest01.ui.IndexActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseMvpActivity<MainPresenter> implements MainContract.View {

    @BindView(R.id.main_et_name)
    EditText mainEtName;
    @BindView(R.id.main_et_pwd)
    EditText mainEtPwd;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        mPresenter = new MainPresenter();
        mPresenter.attachView(this);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void onError(Throwable throwable) {

    }

    @Override
    public void onLoginSuccess(AccessBean accessBean) {
        Toast.makeText(this, accessBean.getAccess_token(), Toast.LENGTH_LONG).show();

        startActivity(new Intent(this, IndexActivity.class));
    }

    private String getName() {
        return mainEtName.getText().toString().trim();
    }

    private String getPwd() {
        return mainEtPwd.getText().toString().trim();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.main_btn_lg)
    public void onClickView() {
        Log.e("TAG", "点击");
        mPresenter.login(getName(), getPwd());
    }
}
