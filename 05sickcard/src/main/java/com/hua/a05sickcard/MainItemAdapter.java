package com.hua.a05sickcard;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainItemAdapter extends MyBaseAdapter<MainItemAdapter.MyViewHolder> {
    private List mDataList;

    public MainItemAdapter(Context context) {
        super(context);
    }

    @Override
    public void notifyDataSetChanged(List dataList) {
        this.mDataList = dataList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new MyViewHolder(getInflater().inflate(R.layout.item_sick, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder myViewHolder, int i) {
        List list = new ArrayList<>();

        if (myViewHolder.isZheDie) {
            myViewHolder.item_sick_zhedie.setText("折叠");
            myViewHolder.item_ll_zhedie.setVisibility(View.VISIBLE);
        } else {
            myViewHolder.item_sick_zhedie.setText("展开");
            myViewHolder.item_ll_zhedie.setVisibility(View.GONE);
        }
        for (int y = 0; y < myViewHolder.count; y++) {
            list.add("");
        }

        int resId = R.anim.layout_animation_fall_down;
        LayoutAnimationController animation = AnimationUtils.loadLayoutAnimation(getInflater().getContext(), resId);
        myViewHolder.item_sick_rcv.setLayoutAnimation(animation);

        MainChildItemAdapter mainChildItemAdapter = new MainChildItemAdapter(getInflater().getContext());
        myViewHolder.item_sick_rcv.setLayoutManager(new LinearLayoutManager(getInflater().getContext(), LinearLayoutManager.VERTICAL, false));
        myViewHolder.item_sick_rcv.setAdapter(mainChildItemAdapter);

        mainChildItemAdapter.notifyDataSetChanged(list);


    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView item_sick_zhedie;
        private RecyclerView item_sick_rcv;
        private LinearLayout item_ll_zhedie;
        private boolean isZheDie = true;
        private int count = 1;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            item_sick_zhedie = itemView.findViewById(R.id.item_sick_zhedie);
            item_sick_rcv = itemView.findViewById(R.id.item_sick_rcv);
            item_ll_zhedie = itemView.findViewById(R.id.item_ll_zhedie);

            item_sick_zhedie.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    isZheDie = !isZheDie;
                    if (isZheDie) {
                        count = 1;
                    } else {
                        count = 4;
                    }
                    notifyDataSetChanged();
                    Log.e("TAG", "当前的折叠点击");
                }
            });


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemClickListioner != null) {
                        onItemClickListioner.onItemClick(v, getLayoutPosition());
                    }
                }
            });

        }


    }

    //自定义监听
    //点击recyclerView某条的监听
    public interface OnItemClickListioner {
        public void onItemClick(View view, int position);
    }

    private OnItemClickListioner onItemClickListioner;

    public void setOnItemClickListioner(OnItemClickListioner onItemClickListioner) {
        this.onItemClickListioner = onItemClickListioner;
    }


}
