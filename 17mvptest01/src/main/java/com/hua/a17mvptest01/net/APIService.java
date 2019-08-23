package com.hua.a17mvptest01.net;

import com.hua.a17mvptest01.bean.AccessBean;

import io.reactivex.Flowable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * @author azheng
 * @date 2018/4/24.
 * GitHub：https://github.com/RookieExaminer
 * Email：wei.azheng@foxmail.com
 * Description：
 */
public interface APIService {

    /**
     * 登陆
     *
     * @param username 账号
     * @param password 密码
     * @return
     */
    @FormUrlEncoded
    @POST("oauth/token")
    Flowable<AccessBean> login(@Field("username") String username,
                               @Field("password") String password, @Field("grant_type") String grant_type);

}
