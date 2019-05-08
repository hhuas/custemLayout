package com.hua.app31custem02;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.hua.app31custem02.sickcard.SickCard2Activity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void jumpSickCard(View view) {
        startActivity(new Intent(MainActivity.this, SickCardActivity.class));
    }

    public void jumpSickCard2(View view) {
        startActivity(new Intent(MainActivity.this, SickCard2Activity.class));
    }
}
