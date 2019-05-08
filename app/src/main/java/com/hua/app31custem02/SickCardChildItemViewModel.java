package com.hua.app31custem02;

import android.databinding.ObservableBoolean;
import android.support.annotation.NonNull;

import me.goldze.mvvmhabit.base.ItemViewModel;

public class SickCardChildItemViewModel extends ItemViewModel<SickCardViewModel> {
    public ObservableBoolean isResult = new ObservableBoolean(false);

    public SickCardChildItemViewModel(@NonNull SickCardViewModel viewModel, boolean isResult) {
        super(viewModel);
        this.isResult.set(isResult);
    }
}
