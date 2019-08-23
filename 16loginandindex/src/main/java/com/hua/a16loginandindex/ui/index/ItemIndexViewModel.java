package com.hua.a16loginandindex.ui.index;

import android.databinding.ObservableField;
import android.support.annotation.NonNull;

import com.hua.a16loginandindex.bean.IndexBean;

import me.goldze.mvvmhabit.base.ItemViewModel;

public class ItemIndexViewModel extends ItemViewModel<IndexViewModel> {

    public ObservableField<IndexBean> entity = new ObservableField<>();

    public ItemIndexViewModel(@NonNull IndexViewModel viewModel, IndexBean entity) {
        super(viewModel);
        this.entity.set(entity);
    }
}
