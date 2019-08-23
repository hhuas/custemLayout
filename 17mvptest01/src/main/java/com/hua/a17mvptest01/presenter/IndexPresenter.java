package com.hua.a17mvptest01.presenter;

import com.hua.a17mvptest01.base.BasePresenter;
import com.hua.a17mvptest01.contract.IndexContract;

import java.util.ArrayList;
import java.util.List;

public class IndexPresenter extends BasePresenter<IndexContract.View> implements IndexContract.Presenter {

    public List dataList=new ArrayList();

    public void initData(){
        for (int i=0;i<5;i++){
            dataList.add(i);
        }
        mView.onLoadSuccess();
    };
}
