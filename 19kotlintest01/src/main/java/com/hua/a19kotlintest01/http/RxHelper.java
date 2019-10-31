package com.hua.a19kotlintest01.http;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * @author Yu, Yang
 */
public class RxHelper {
    /**
     * 从其他线程转到主线程.
     *
     * @param <T> t
     * @return FlowableTransformer
     */
    public static <T> FlowableTransformer<T, T> ioToMain() {
        return upstream -> upstream.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    public static <T> FlowableTransformer<HttpResult<T>, T> handResult() {
        return upstream -> upstream.flatMap(tHttpResult -> {
            if (tHttpResult.getStatus() == HttpCode.SUCCESS || tHttpResult.getStatus() == HttpCode.QUERY_USER_PAY_STATUS) {
                ///createData(tHttpResult.getData());
                return Flowable.just(tHttpResult.getData());
            } else {
                return Flowable.error(new ApiException(tHttpResult.getStatus(), tHttpResult.getMessage()));
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }



    private static <T> Flowable<T> createData(final T data) {
        return Flowable.create(e -> {
            e.onNext(data);
            e.onComplete();
        }, BackpressureStrategy.ERROR);
    }
}
