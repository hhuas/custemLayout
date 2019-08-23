package com.hua.a17mvptest01.ui;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.hua.a17mvptest01.R;
import com.hua.a17mvptest01.base.BaseMvpActivity;
import com.hua.a17mvptest01.contract.IndexContract;
import com.hua.a17mvptest01.presenter.IndexPresenter;
import com.hua.a17mvptest01.presenter.MainPresenter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import butterknife.BindView;

public class IndexActivity extends BaseMvpActivity<IndexPresenter> implements IndexContract.View {

    @BindView(R.id.index_smart_layout)
    SmartRefreshLayout indexSmartLayout;
    @BindView(R.id.index_lv_content)
    ListView indexLvContent;

    private MyAdapter myAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_index;
    }

    @Override
    public void initView() {
        mPresenter = new IndexPresenter();
        mPresenter.attachView(this);
        mPresenter.initData();
        initListener();

        indexLvContent.setAdapter(myAdapter);
    }

    private void initListener() {
        indexSmartLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                mPresenter.dataList.clear();
                mPresenter.initData();
                refreshLayout.finishRefresh();
            }
        });
        indexSmartLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                mPresenter.initData();
                refreshLayout.finishLoadMore();
            }
        });
    }

    @Override
    public void onLoadSuccess() {
        if (myAdapter == null) {
            myAdapter = new MyAdapter();
        }
        myAdapter.notifyDataSetChanged();
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

    public class MyAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return mPresenter.dataList.size();
        }

        @Override
        public Object getItem(int position) {
            return mPresenter.dataList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder = null;
            if (convertView == null) {
                convertView = View.inflate(IndexActivity.this, R.layout.item_index, null);
                viewHolder = new ViewHolder(convertView);
                viewHolder.item_index_iv_img = convertView.findViewById(R.id.item_index_iv_img);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            return convertView;
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            ImageView item_index_iv_img;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
            }
        }
    }
}
