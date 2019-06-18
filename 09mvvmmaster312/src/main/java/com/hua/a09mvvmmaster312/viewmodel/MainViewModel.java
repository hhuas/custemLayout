package com.hua.a09mvvmmaster312.viewmodel;

import android.app.Application;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;
import android.support.annotation.NonNull;
import android.util.Log;

import com.hua.a09mvvmmaster312.BR;
import com.hua.a09mvvmmaster312.R;
import com.hua.a09mvvmmaster312.data.MyDataRepository;
import com.hua.a09mvvmmaster312.data.source.http.service.MyApiService;
import com.hua.a09mvvmmaster312.entity.CatagoryEntity;
import com.hua.a09mvvmmaster312.entity.MyBaseResponce;
import com.hua.a09mvvmmaster312.itemviewmodel.MainItemViewModel;
import com.hua.a09mvvmmaster312.utils.RetrofitClient;

import java.util.List;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import me.goldze.mvvmhabit.base.BaseViewModel;
import me.goldze.mvvmhabit.http.ResponseThrowable;
import me.goldze.mvvmhabit.utils.RxUtils;
import me.goldze.mvvmhabit.utils.ToastUtils;
import me.tatarka.bindingcollectionadapter2.BindingRecyclerViewAdapter;
import me.tatarka.bindingcollectionadapter2.ItemBinding;

public class MainViewModel extends BaseViewModel<MyDataRepository> {

    public MainViewModel(@NonNull Application application, MyDataRepository myDataRepository) {
        super(application, myDataRepository);
    }

    //给RecyclerView添加ObservableList
    public ObservableList<MainItemViewModel> observableList = new ObservableArrayList<>();
    //给RecyclerView添加ItemBinding
    public ItemBinding<MainItemViewModel> itemBinding = ItemBinding.of(BR.viewModel, R.layout.item_main);
    //给RecyclerView添加Adpter，请使用自定义的Adapter继承BindingRecyclerViewAdapter，重写onBindBinding方法，里面有你要的Item对应的binding对象
    public final BindingRecyclerViewAdapter<MainItemViewModel> adapter = new BindingRecyclerViewAdapter<>();

    public void initTest() {
        model.getTestRepository("0", "0")
                .compose(RxUtils.bindToLifecycle(getLifecycleProvider())) //请求与View周期同步（过度期，尽量少使用）
                .compose(RxUtils.schedulersTransformer()) //线程调度
                .compose(RxUtils.exceptionTransformer()) // 网络错误的异常转换, 这里可以换成自己的ExceptionHandle
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        showDialog("正在请求...");
                    }
                }).subscribe(new Consumer<MyBaseResponce<List<CatagoryEntity>>>() {

                                 @Override
                                 public void accept(MyBaseResponce<List<CatagoryEntity>> listMyBaseResponce) throws Exception {
                                     List<CatagoryEntity> datas = listMyBaseResponce.getDatas();
                                     for(CatagoryEntity data:datas){
                                         MainItemViewModel mainItemViewModel = new MainItemViewModel(MainViewModel.this, data);
                                         observableList.add(mainItemViewModel);
                                     }

                                 }
                             }
                , new Consumer<ResponseThrowable>() {
                    @Override
                    public void accept(ResponseThrowable throwable) throws Exception {
                        //关闭对话框
                        dismissDialog();
                        ToastUtils.showShort(throwable.message);
                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {
                        //关闭对话框
                        dismissDialog();
                        //请求刷新完成收回
                    }
                });
//        RetrofitClient.getInstance().create(MyApiService.class)
//                .getTestRepository("0","1")
//                .compose(RxUtils.bindToLifecycle(getLifecycleProvider())) //请求与View周期同步（过度期，尽量少使用）
//                .compose(RxUtils.schedulersTransformer()) //线程调度
//                .compose(RxUtils.exceptionTransformer()) // 网络错误的异常转换, 这里可以换成自己的ExceptionHandle
//                .doOnSubscribe(new Consumer<Disposable>() {
//                    @Override
//                    public void accept(Disposable disposable) throws Exception {
//                        showDialog("正在请求...");
//                    }
//                }).subscribe(new Consumer<MyBaseResponce<String>>() {
//                                 @Override
//                                 public void accept(MyBaseResponce<String> myBaseResponce) throws Exception {
//                                     Log.e("TAG", "myBaseResponce.getMessage():" + myBaseResponce.getMessage());
//                                 }
//                             }
//                , new Consumer<ResponseThrowable>() {
//                    @Override
//                    public void accept(ResponseThrowable throwable) throws Exception {
//                        //关闭对话框
//                        dismissDialog();
//                        ToastUtils.showShort(throwable.message);
//                    }
//                }, new Action() {
//                    @Override
//                    public void run() throws Exception {
//                        //关闭对话框
//                        dismissDialog();
//                        //请求刷新完成收回
//                    }
//                });

    }
}
