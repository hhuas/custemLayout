package com.hua.a09mvvmmaster312.data;

import android.support.annotation.VisibleForTesting;

import com.hua.a09mvvmmaster312.data.source.HttpDataSource;
import com.hua.a09mvvmmaster312.data.source.LocalDataSource;
import com.hua.a09mvvmmaster312.entity.CatagoryEntity;
import com.hua.a09mvvmmaster312.entity.MyBaseResponce;

import java.util.List;

import io.reactivex.Observable;
import me.goldze.mvvmhabit.base.BaseModel;

public class MyDataRepository extends BaseModel implements HttpDataSource, LocalDataSource {
    private volatile static MyDataRepository INSTANCE=null;
    private final HttpDataSource mHttpDataSource;
    private final LocalDataSource mLocalDataSource;

    public MyDataRepository(HttpDataSource mHttpDataSource, LocalDataSource mLocalDataSource) {
        this.mHttpDataSource = mHttpDataSource;
        this.mLocalDataSource = mLocalDataSource;
    }

    public static MyDataRepository getInstance(HttpDataSource mHttpDataSource, LocalDataSource mLocalDataSource){
        if(INSTANCE==null){
            synchronized (MyDataRepository.class){
                if(INSTANCE==null){
                    INSTANCE=new MyDataRepository(mHttpDataSource,mLocalDataSource);
                }
            }
        }
        return INSTANCE;
    }

    @VisibleForTesting
    public static void destroyInstance() {
        INSTANCE = null;
    }

    @Override
    public Observable<MyBaseResponce<List<CatagoryEntity>>> getTestRepository(String level, String type) {
        return mHttpDataSource.getTestRepository(level,type);
    }
}
