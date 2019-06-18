package com.hua.a09mvvmmaster312.data.source.local;

import com.hua.a09mvvmmaster312.data.source.LocalDataSource;

public class LocalDataSourceImpl implements LocalDataSource {

    private volatile static LocalDataSourceImpl INSTANCE = null;

    public static LocalDataSourceImpl getInstance() {
        if (INSTANCE == null) {
            synchronized (LocalDataSourceImpl.class) {
                if (INSTANCE == null) {
                    INSTANCE = new LocalDataSourceImpl();
                }
            }
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }

    private LocalDataSourceImpl() {
        //数据库Helper构建
    }
}
