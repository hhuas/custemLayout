package com.hua.a10indexview;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView tv_word;
    private IndexView iv_index;
    private ListView lv_words;

    private Handler handler = new Handler();

    private String[] citys = {"沈阳市", "葫芦岛市", " 大连市", " 盘锦市 ", "鞍山市", " 铁岭市", " 本溪市", " 丹东市",
            "抚顺市", " 锦州市 ", "辽阳市", " 阜新市 ", "调兵山市", " 朝阳市", " 海城市", " 北票市",
            "盖州市 ", "凤城市", " 庄河市", " 凌源市 ", "开原市", " 兴城市 ", "新民市", " 大石桥市",
            "东港市", " 北宁市", " 瓦房店市 ", "普兰店市", " 凌海市", " 灯塔市", " 营口市"};
    private List<UnitEntity> units;
    private IndexAdapter indexAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initListener();
        initData();
        initListView();

    }

    private void initData() {
        units = new ArrayList<>();
        for (int i = 0; i < citys.length; i++) {
            units.add(new UnitEntity(citys[i].trim()));
        }
        Collections.sort(units, new Comparator<UnitEntity>() {
            @Override
            public int compare(UnitEntity o1, UnitEntity o2) {
                return o1.getPinyin().compareTo(o2.getPinyin());
            }
        });
    }

    private void initListView() {
        indexAdapter = new IndexAdapter();
        lv_words.setAdapter(indexAdapter);
    }

    private void initListener() {
        iv_index.setOnIndexChangeListener(new IndexView.OnIndexChangeListener() {
            @Override
            public void onIndexChanger(String word) {
                updateWord(word);
                updateListSelect(word);
            }
        });

        lv_words.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.e("TAG", units.get(position).getName());
            }
        });

    }

    private void updateListSelect(String word) {
        for (int i = 0; i < units.size(); i++) {
            String pinyin = units.get(i).getPinyin().substring(0, 1);
            if (pinyin.equals(word)) {
                lv_words.setSelection(i);
                return;
            }
        }
    }

    private void updateWord(String word) {
        handler.removeCallbacksAndMessages(null);
        if (word != null) {
            tv_word.setText(word);
            tv_word.setVisibility(View.VISIBLE);
        } else {
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    tv_word.setVisibility(View.GONE);
                }
            }, 3000);
        }
    }

    private void initView() {
        tv_word = findViewById(R.id.tv_word);
        iv_index = findViewById(R.id.iv_index);
        lv_words = findViewById(R.id.lv_words);
    }

    class IndexAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return units.size();
        }

        @Override
        public Object getItem(int position) {
            return units.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
            if (convertView == null) {
                convertView = View.inflate(MainActivity.this, R.layout.item_units, null);
                viewHolder = new ViewHolder();
                viewHolder.item_tv_word_pinyin = convertView.findViewById(R.id.item_tv_word_pinyin);
                viewHolder.item_tv_word_name = convertView.findViewById(R.id.item_tv_word_name);
                viewHolder.v_line = convertView.findViewById(R.id.v_line);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            UnitEntity unitEntity = units.get(position);
            String name = unitEntity.getName();
            String pinyin = unitEntity.getPinyin().substring(0, 1);
            viewHolder.item_tv_word_name.setText(name);
            viewHolder.item_tv_word_pinyin.setText(pinyin);

            if (position == 0) {
                viewHolder.item_tv_word_pinyin.setVisibility(View.VISIBLE);
            } else {
                //得到前一个对应的字母，如果当前的字母和前一个字母相同，就隐藏当前字母
                String prePinyin = units.get(position - 1).getPinyin().substring(0, 1);
                if (pinyin.equals(prePinyin)) {
                    viewHolder.item_tv_word_pinyin.setVisibility(View.GONE);
                } else {
                    viewHolder.item_tv_word_pinyin.setVisibility(View.VISIBLE);
                }
                //得到后一个对应的字母，如果当前的字母和后一个字母相同，就隐藏当前分隔线
                if (position + 1 < units.size()) {
                    String nextPinyin = units.get(position + 1).getPinyin().substring(0, 1);
                    if (pinyin.equals(nextPinyin)) {
                        viewHolder.v_line.setVisibility(View.VISIBLE);
                    } else {
                        viewHolder.v_line.setVisibility(View.GONE);
                    }
                }

            }
            return convertView;
        }
    }

    static class ViewHolder {
        TextView item_tv_word_pinyin;
        TextView item_tv_word_name;
        View v_line;
    }

    private CityListPopWindow cityListPopWindow = null;

    public void citylist(View view) {
        String[] hotCitys={"沈阳市", "葫芦岛市", "大连市", "盘锦市 ", "鞍山市", "铁岭市", "本溪市"};
            cityListPopWindow = new CityListPopWindow(this);
            cityListPopWindow.setHotCitys(hotCitys);
            cityListPopWindow.setCitys(citys);
            cityListPopWindow.setOnCityClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Toast.makeText(MainActivity.this, units.get(position).getName(), Toast.LENGTH_LONG).show();
                    cityListPopWindow.dissmis();
                }
            });
            cityListPopWindow.show();

    }
}
