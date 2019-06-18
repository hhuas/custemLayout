package com.hua.a09mvvmmaster312.data.source;

import com.hua.a09mvvmmaster312.entity.CatagoryEntity;
import com.hua.a09mvvmmaster312.entity.MyBaseResponce;

import java.util.List;

import io.reactivex.Observable;

public interface HttpDataSource {

    Observable<MyBaseResponce<List<CatagoryEntity>>> getTestRepository(String level, String type);
}
