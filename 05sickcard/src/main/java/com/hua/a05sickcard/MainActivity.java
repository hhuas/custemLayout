package com.hua.a05sickcard;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView main_rcv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        main_rcv = findViewById(R.id.main_rcv);

        List list = new ArrayList();
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        MainItemAdapter mainItemAdapter = new MainItemAdapter(this);
        main_rcv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        main_rcv.setAdapter(mainItemAdapter);
        mainItemAdapter.notifyDataSetChanged(list);

        main_rcv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                Log.e("TAG", "newState:" + newState);
            }
        });
    }
}
