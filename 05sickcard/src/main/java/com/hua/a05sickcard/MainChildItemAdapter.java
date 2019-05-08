package com.hua.a05sickcard;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class MainChildItemAdapter extends MyBaseAdapter<MainChildItemAdapter.MyViewHolder> {
    private List mDataList;

    public MainChildItemAdapter(Context context) {
        super(context);
    }

    @Override
    public void notifyDataSetChanged(List dataList) {
        this.mDataList = dataList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new MyViewHolder(getInflater().inflate(R.layout.item_child_sick, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
