package com.hua.a12statusbar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.gyf.immersionbar.ImmersionBar;

public class Main2Activity extends AppCompatActivity {
    private ImageView iv_main2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ImmersionBar.with(this).init();
        setContentView(R.layout.activity_main2);
        String url = "http://qn.yushangai.top/579b6063-440c-439f-862b-248509f43334.jpg";
        iv_main2 = findViewById(R.id.iv_main2);
        Glide.with(this).load(url).into(iv_main2);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
