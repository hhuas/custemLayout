package com.hua.a19kotlintest01

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.hua.a19kotlintest01.http.RetrofitUtil
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        main_btn01.setOnClickListener {
            Log.e("TAG", "main_btn01");
            main_btn05.visibility = View.GONE;
        }
        main_btn02.setOnClickListener {
        }
        main_btn03.setOnClickListener {
        }
        main_btn04.setOnClickListener {
        }
        main_btn05.setOnClickListener {
        }
        main_btn06.setOnClickListener {
        }
        val key:String="1be865c0e67e3"
        RetrofitUtil.retrofitService.calenderDay("",key)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe ()
    }
}
