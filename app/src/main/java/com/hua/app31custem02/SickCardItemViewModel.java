package com.hua.app31custem02;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableInt;
import android.databinding.ObservableList;
import android.support.annotation.NonNull;
import android.view.View;

import me.goldze.mvvmhabit.base.ItemViewModel;
import me.goldze.mvvmhabit.binding.command.BindingAction;
import me.goldze.mvvmhabit.binding.command.BindingCommand;
import me.tatarka.bindingcollectionadapter2.BindingRecyclerViewAdapter;
import me.tatarka.bindingcollectionadapter2.ItemBinding;

public class SickCardItemViewModel extends ItemViewModel<SickCardViewModel> {

    public SickCardItemViewModel(@NonNull SickCardViewModel viewModel,boolean isResult) {
        super(viewModel);
        this.isResult.set(isResult);
        initData();
    }

    public ObservableBoolean isZheDie = new ObservableBoolean(true);
    public ObservableInt vis = new ObservableInt(View.VISIBLE);
    public ObservableInt gone = new ObservableInt(View.GONE);
    public ObservableBoolean isResult = new ObservableBoolean();

    public ObservableList<SickCardChildItemViewModel> dataObservableList = new ObservableArrayList<>();
    public ItemBinding<SickCardChildItemViewModel> dataItemBinding = ItemBinding.of(BR.viewModel, R.layout.item_child_sick_card);
    public final BindingRecyclerViewAdapter<SickCardChildItemViewModel> dataAdapter = new BindingRecyclerViewAdapter<>();

    public void initData() {
        dataObservableList.clear();
        dataAdapter.notifyDataSetChanged();
        for (int i = 0; i < 4; i++) {
            SickCardChildItemViewModel sickCardChildItemViewModel = new SickCardChildItemViewModel(viewModel, isResult.get());
            dataObservableList.add(sickCardChildItemViewModel);
            if (isZheDie.get()) {
                return;
            }

        }

    }

    public BindingCommand zhedie = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            isZheDie.set(!isZheDie.get());
            initData();

        }
    });
}
