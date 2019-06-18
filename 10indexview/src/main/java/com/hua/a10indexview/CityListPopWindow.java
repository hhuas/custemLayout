package com.hua.a10indexview;

import android.content.Context;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CityListPopWindow {
    private Context context;

    AlertDialog alert;
    AlertDialog.Builder dialog;

    IndexView iv_index2;
    ListView lv_words2;
    TextView tv_word2;

    GridView gv_popwindow;

    private String[] citys;
    private String[] hotCitys;
    private List<UnitEntity> units;

    private IndexAdapter indexAdapter;
    private HotCitysAdapter hotCitysAdapter;
    private Handler handler = new Handler();


    public CityListPopWindow(Context context) {
        this.context = context;
        View layout = LayoutInflater.from(context).inflate(R.layout.activity_main2, null);
        //在对话框里加载布局：setView()方法
        dialog = new AlertDialog.Builder(context, R.style.style_dialog);
        dialog.setCancelable(true).setView(layout);//这里加载布局//如果是false，点击其他位置或者返回键无效，这个地方默认为true
        alert = dialog.create();
        //设置对话框的宽和高(注意，设置长宽还需要设置R.style.style_dialog，否则会出现很多问题)
        Window window = alert.getWindow();
        window.getDecorView().setPadding(0, 0, 0, 0);//void setPadding (int left, int top,int right,int bottom)
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setAttributes(lp);
        window.setGravity(Gravity.CENTER);//对话框出现的位置

        initView(layout);
        initListener();

    }

    private void initListener() {
        iv_index2.setOnIndexChangeListener(new IndexView.OnIndexChangeListener() {
            @Override
            public void onIndexChanger(String word) {
                updateWord(word);
                updateListSelect(word);
            }
        });

    }

    public void setOnCityClickListener(AdapterView.OnItemClickListener onCityClickListener) {
        lv_words2.setOnItemClickListener(onCityClickListener);
    }

    private void updateListSelect(String word) {
        for (int i = 0; i < units.size(); i++) {
            String pinyin = units.get(i).getPinyin().substring(0, 1);
            if (pinyin.equals(word)) {
                lv_words2.setSelection(i);
                return;
            }
        }
    }

    private void updateWord(String word) {
        handler.removeCallbacksAndMessages(null);
        if (word != null) {
            tv_word2.setText(word);
            tv_word2.setVisibility(View.VISIBLE);
        } else {
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    tv_word2.setVisibility(View.GONE);
                }
            }, 3000);
        }
    }


    private void initView(View layout) {
        iv_index2 = layout.findViewById(R.id.iv_index2);
        lv_words2 = layout.findViewById(R.id.lv_words2);
        tv_word2 = layout.findViewById(R.id.tv_word2);
        gv_popwindow = layout.findViewById(R.id.gv_popwindow);
    }

    public void setCitys(String[] citys) {
        this.citys = citys;
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

        indexAdapter = new IndexAdapter();
        lv_words2.setAdapter(indexAdapter);
    }

    public void setHotCitys(String[] hotCitys) {
        this.hotCitys = hotCitys;
        hotCitysAdapter = new HotCitysAdapter();
        gv_popwindow.setAdapter(hotCitysAdapter);
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
                convertView = View.inflate(context, R.layout.item_units, null);
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

    class HotCitysAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return hotCitys.length;
        }

        @Override
        public Object getItem(int position) {
            return hotCitys[position];
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            HotCityViewHolder viewHolder;
            if (convertView == null) {
                convertView = View.inflate(context, R.layout.item_citylist_hot_citys, null);
                viewHolder = new HotCityViewHolder();
                viewHolder.item_tv_hot_city = convertView.findViewById(R.id.item_tv_hot_city);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (HotCityViewHolder) convertView.getTag();
            }
            viewHolder.item_tv_hot_city.setText(hotCitys[position]);

            return convertView;
        }
    }

    static class HotCityViewHolder {
        TextView item_tv_hot_city;
    }


    public void show() {
        alert.show();
    }

    public void dissmis() {
        if (alert != null) {
            alert.dismiss();
        }
    }

}
