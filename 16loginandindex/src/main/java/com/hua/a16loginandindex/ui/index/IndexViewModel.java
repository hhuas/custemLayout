package com.hua.a16loginandindex.ui.index;

import android.app.Application;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableField;
import android.databinding.ObservableList;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.hua.a16loginandindex.BR;
import com.hua.a16loginandindex.R;
import com.hua.a16loginandindex.bean.AccessBean;
import com.hua.a16loginandindex.bean.IndexBean;
import com.hua.a16loginandindex.utils.FlushResultBean;
import com.hua.a16loginandindex.utils.HomeBannerEntity;
import com.hua.a16loginandindex.utils.MyBaseResponse;
import com.hua.a16loginandindex.utils.MyRetrofitClient;
import com.hua.a16loginandindex.utils.MyService;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.List;

import io.reactivex.functions.Consumer;
import me.goldze.mvvmhabit.base.BaseViewModel;
import me.tatarka.bindingcollectionadapter2.BindingRecyclerViewAdapter;
import me.tatarka.bindingcollectionadapter2.ItemBinding;

import static me.goldze.mvvmhabit.utils.Utils.getContext;

public class IndexViewModel extends BaseViewModel {
    public IndexViewModel(@NonNull Application application) {
        super(application);
    }

    public ObservableList<ItemIndexViewModel> dataObservableList = new ObservableArrayList<>();
    public ItemBinding<ItemIndexViewModel> dataItemBinding = ItemBinding.of(BR.viewModel, R.layout.item_index);
    public final BindingRecyclerViewAdapter<ItemIndexViewModel> dataAdapter = new BindingRecyclerViewAdapter<>();

    public ObservableField<SmartRefreshLayout> smartRefreshOB = new ObservableField<>();
    private int position = 0;

    public ObservableField<AccessBean> accessBeanOB = new ObservableField<>();

    public void initData() {
        for (int i = 0; i < 5; i++) {
            IndexBean indexBean = new IndexBean();
            position++;
            indexBean.setPosition(position);
            ItemIndexViewModel itemIndexViewModel = new ItemIndexViewModel(IndexViewModel.this, indexBean);
            dataObservableList.add(itemIndexViewModel);
        }
    }



    public void initSmart() {
        smartRefreshOB.get().setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                dataObservableList.clear();
                position = 0;
                initData();
                refreshLayout.finishRefresh();
            }
        });

        smartRefreshOB.get().setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                initData();
                refreshLayout.finishLoadMore();
            }
        });
    }

}
