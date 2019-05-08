package com.hua.app31custem02.sickcard;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.hua.app31custem02.R;

public class SickCard2Activity extends AppCompatActivity {
    private ListView sick2_lv;
    private MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sick_card2);
        initView();
        initData();
    }


    private void initData() {
        myAdapter = new MyAdapter();
        sick2_lv.setAdapter(myAdapter);
    }

    private void initView() {
        sick2_lv = findViewById(R.id.sick2_lv);
    }

    class MyAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return 5;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
            if (convertView == null) {
                viewHolder = new ViewHolder();
                convertView = View.inflate(SickCard2Activity.this, R.layout.item_sick2, null);
                viewHolder.sick2_lv = convertView.findViewById(R.id.sick2_lv);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

//            viewHolder.sick2_lv.setAdapter(new MyAdapter2());
            return convertView;
        }
    }

    class ViewHolder {
        ListView sick2_lv;
        boolean isZheDie = true;

        class MyAdapter2 extends BaseAdapter {
            @Override
            public int getCount() {
                return isZheDie ? 1 : 4;
            }

            @Override
            public Object getItem(int position) {
                return null;
            }

            @Override
            public long getItemId(int position) {
                return 0;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                if(convertView==null){
                    convertView=View.inflate(SickCard2Activity.this,R.layout.item_child_sick2,null);
                }
                return convertView;
            }
        }

    }


}
