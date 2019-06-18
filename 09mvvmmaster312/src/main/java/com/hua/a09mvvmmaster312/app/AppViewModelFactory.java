package com.hua.a09mvvmmaster312.app;

import android.annotation.SuppressLint;
import android.app.Application;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;

import com.hua.a09mvvmmaster312.data.MyDataRepository;
import com.hua.a09mvvmmaster312.viewmodel.MainViewModel;

public class AppViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    @SuppressLint("StaticFieldLeak")
    private volatile static AppViewModelFactory INSTANCE;

    private final Application mApplication;
    private final MyDataRepository myDataRepository;

    public AppViewModelFactory(Application mApplication, MyDataRepository myDataRepository) {
        this.mApplication = mApplication;
        this.myDataRepository = myDataRepository;
    }

    public static AppViewModelFactory getInstance(Application application){
         if(INSTANCE==null){
             synchronized (AppViewModelFactory.class){
                 if(INSTANCE==null){
                     INSTANCE=new AppViewModelFactory(application,Injection.provideMyDataRepository());
                 }
             }
         }
         return INSTANCE;
    }

    @VisibleForTesting
    public static void destroyInstance() {
        INSTANCE = null;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(MainViewModel.class)) {
            return (T) new MainViewModel(mApplication, myDataRepository);
        }
//        else if (modelClass.isAssignableFrom(LoginViewModel.class)) {
//            return (T) new LoginViewModel(mApplication, mRepository);
//        }
        throw new IllegalArgumentException("Unknown ViewModel class: " + modelClass.getName());
    }
}
