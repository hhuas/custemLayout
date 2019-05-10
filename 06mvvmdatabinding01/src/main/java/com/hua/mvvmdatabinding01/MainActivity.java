package com.hua.mvvmdatabinding01;

import android.databinding.DataBindingUtil;
import android.databinding.ObservableField;
import android.databinding.ViewDataBinding;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.hua.mvvmdatabinding01.databinding.ActivityMainBinding;
import com.hua.mvvmdatabinding01.model.User;
import com.hua.mvvmdatabinding01.model.User2;

import java.util.Random;

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
    }
}
