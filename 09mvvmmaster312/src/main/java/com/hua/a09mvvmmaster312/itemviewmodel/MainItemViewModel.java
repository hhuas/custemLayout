package com.hua.a09mvvmmaster312.itemviewmodel;

import android.databinding.ObservableField;
import android.support.annotation.NonNull;

import com.hua.a09mvvmmaster312.entity.CatagoryEntity;
import com.hua.a09mvvmmaster312.viewmodel.MainViewModel;

import me.goldze.mvvmhabit.base.ItemViewModel;

public class MainItemViewModel extends ItemViewModel<MainViewModel> {
    public ObservableField<CatagoryEntity> entity=new ObservableField<>();

    public MainItemViewModel(@NonNull MainViewModel viewModel, CatagoryEntity entity) {
        super(viewModel);
        this.entity.set(entity);
    }
}
