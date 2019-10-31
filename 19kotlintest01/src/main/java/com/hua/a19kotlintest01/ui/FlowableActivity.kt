package com.hua.a19kotlintest01.ui

import android.app.Activity
import android.os.Bundle
import android.util.Log
import com.hua.a19kotlintest01.R
import com.hua.a19kotlintest01.http.RxHelper
import io.reactivex.Flowable
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer
import org.reactivestreams.Subscriber
import org.reactivestreams.Subscription
import java.util.concurrent.TimeUnit

/**
 * FileName: FlowableActivity
 * Author:   花恩成
 * Date:     2019/9/30 9:34
 * Description:
 */
class FlowableActivity : Activity() {
//    private lateinit var subscribe: Subscriber<Disposable>
    private lateinit var subscribe: Disposable
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flowable)
        Log.e("TAG", "str:" + "haha")
        var i: Int = 0
         subscribe = Flowable.interval(3, TimeUnit.SECONDS)
//                .filter { true }
                .map {
                    i = i + it.toInt()
                    return@map "haha" + i
                }
                .filter {
                    if (i > 5) {
                        finish()
                        return@filter true
                    } else {
                        return@filter false
                    }
                }
                .doOnSubscribe(Consumer {
                })
                .compose(RxHelper.ioToMain())
                .subscribe(this::log)
    }

    private fun log(str: String) {
        Log.e("TAG", "str:" + str)
    }

    override fun onDestroy() {
        super.onDestroy()
        subscribe.dispose()

    }
}