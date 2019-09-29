package com.hua.a20cuspopwindows;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.hua.a20cuspopwindows.bean.NameBean;
import com.hua.a20cuspopwindows.pop.PopwindowGrid;
import com.hua.a20cuspopwindows.pop.PopwindowList;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private PopwindowList popwindowList;
    private List<NameBean> nameList;

    private PopwindowGrid popwindowGrid;
    private List<NameBean> nameList2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nameList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            NameBean nameBean = new NameBean();
            nameBean.setName("十八:" + i);
            nameList.add(nameBean);
        }
        popwindowList = new PopwindowList(this);

        nameList2=new ArrayList<>();
        NameBean nameBean2 = new NameBean();
        NameBean nameBean3 = new NameBean();
        NameBean nameBean4 = new NameBean();
        nameBean2.setName("少油");
        nameBean3.setName("少盐");
        nameBean4.setName("青菜");

        nameList2.add(nameBean2);
        nameList2.add(nameBean3);
        nameList2.add(nameBean4);

        popwindowGrid=new PopwindowGrid(this);
        popwindowGrid.setContentData(nameList2);

    }

    public void testpop(View view) {
        popwindowList.setContentData(nameList);
        popwindowList.setItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Toast.makeText(MainActivity.this, nameList.get(position).getName(), Toast.LENGTH_SHORT).show();
                popwindowList.checkPosition = position;
                popwindowList.dissmis();
            }
        });
        popwindowList.show();
    }

    public void testPop2(View view){
        popwindowGrid.show();
    }

}
