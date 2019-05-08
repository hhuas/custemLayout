package com.hua.app31custem02;

import android.app.Application;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableList;
import android.support.annotation.NonNull;

import me.goldze.mvvmhabit.base.BaseViewModel;
import me.tatarka.bindingcollectionadapter2.BindingRecyclerViewAdapter;
import me.tatarka.bindingcollectionadapter2.ItemBinding;

public class SickCardViewModel extends BaseViewModel {

    public SickCardViewModel(@NonNull Application application) {
        super(application);
        initData();
    }

    public ObservableBoolean isResult = new ObservableBoolean(false);
    public ObservableList<SickCardItemViewModel> dataObservableList = new ObservableArrayList<>();
    public ItemBinding<SickCardItemViewModel> dataItemBinding = ItemBinding.of(BR.viewModel, R.layout.item_sick_card);
    public final BindingRecyclerViewAdapter<SickCardItemViewModel> dataAdapter = new BindingRecyclerViewAdapter<>();

    public void initData() {
        for (int i = 0; i < 5; i++) {
            if (i % 2 == 0) {
                isResult.set(true);
            } else {
                isResult.set(false);
            }
            SickCardItemViewModel sickCardItemViewModel = new SickCardItemViewModel(SickCardViewModel.this,isResult.get());
            dataObservableList.add(sickCardItemViewModel);
        }
    }
}
