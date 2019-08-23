package com.hua.a17mvptest01.contract;

import com.hua.a17mvptest01.base.BaseView;

public interface IndexContract {

    interface Model{
    }

    interface View extends BaseView {
        @Override
        void showLoading();

        @Override
        void hideLoading();

        @Override
        void onError(Throwable throwable);

        void onLoadSuccess();
    }

    interface Presenter{
    }
}
