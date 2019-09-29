package com.lianxin.a21dbflow01;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.lianxin.a21dbflow01.db.data.UserData;
import com.lianxin.a21dbflow01.db.data.UserData_Table;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        UserData userData = new UserData();

        userData.setId(2);
        userData.setName("小明2");
//        userData.insert();// 插入user
        userData.save();
        userData.setId(1);
        userData.setName("小明2");
        userData.save();

    }

    public void testDb(View view) {
//        List<UserData> userDatas = SQLite.select().from(UserData.class).queryList();
//        for (UserData data : userDatas) {
//            Log.e("TAG", "id:" + data.getId() + "--name:" + data.getName());
//        }
        List<UserData> userDatas2 = SQLite.select().from(UserData.class)
                .where(UserData_Table.name.like("%2%")).queryList();
        for (UserData data : userDatas2) {
            Log.e("TAG", "id2:" + data.getId() + "--name2:" + data.getName());
        }
    }
}
