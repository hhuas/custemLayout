package com.hua.mvvmhabittest01;

import android.content.Intent;
import android.database.Observable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.hua.mvvmhabittest01.entity.MyBaseResponse;
import com.hua.mvvmhabittest01.service.MyApiService;
import com.hua.mvvmhabittest01.ui.Retrofit2Activity;
import com.hua.mvvmhabittest01.utils.RetrofitClient;

import java.util.HashMap;
import java.util.Map;

import retrofit2.http.QueryMap;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void jumpToRetrofit2(View view) {
        startActivity(new Intent(MainActivity.this, Retrofit2Activity.class));
    }
}
