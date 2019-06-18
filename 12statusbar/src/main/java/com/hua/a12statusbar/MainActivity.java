package com.hua.a12statusbar;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class MainActivity extends BaseActivity {
    private ImageView iv_main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtil.setRootViewFitsSystemWindows(this, false);
    }

    @Override
    protected void initEvent() {
        String url = "http://qn.yushangai.top/579b6063-440c-439f-862b-248509f43334.jpg";
        Glide.with(this).load(url).into(iv_main);
//        StatusBarUtil.setTranslucentStatus(MainActivity.this);//透明状态栏
//        StatusBarUtil.setStatusBarDarkTheme(MainActivity.this, true);
        StatusBarUtil.setStatusBarColor(this, 0x55000000);
    }

    @Override
    protected void setOrientation() {
        iv_main = findViewById(R.id.iv_main);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    public void jumpto(View view) {

        startActivity(new Intent(MainActivity.this, Main2Activity.class));
    }
}
