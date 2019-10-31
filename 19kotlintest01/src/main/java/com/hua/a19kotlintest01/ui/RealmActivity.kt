package com.hua.a19kotlintest01.ui

import android.app.Activity
import android.os.Bundle
import io.realm.Realm

/**
 * FileName: RealmActivity
 * Author:   花恩成
 * Date:     2019/9/30 9:04
 * Description:
 */
class RealmActivity : Activity() {

    private lateinit var realm: Realm

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        realm= Realm.getDefaultInstance()
    }

    override fun onDestroy() {
        super.onDestroy()
        realm.close()
    }
}