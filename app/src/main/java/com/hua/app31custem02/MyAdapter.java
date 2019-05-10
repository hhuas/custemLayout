package com.hua.app31custem02;

import android.databinding.BindingAdapter;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

public class MyAdapter {

    @BindingAdapter("rcvScrollListener")
    public static void onScrollChangeaaa(RecyclerView recyclerView,String string) {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                Log.e("TAG", "newState:" + newState);
            }
        });
    }

    @BindingAdapter("scrlistener")
    public static void setLineManager(RecyclerView recyclerView,String string) {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                Log.e("TAG", "newState:" + newState);
            }
        });
    }
}
