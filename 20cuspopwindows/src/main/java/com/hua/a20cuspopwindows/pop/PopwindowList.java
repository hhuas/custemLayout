package com.hua.a20cuspopwindows.pop;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.hua.a20cuspopwindows.bean.NameBean;
import com.hua.a20cuspopwindows.R;

import java.util.List;

public class PopwindowList {
    private Context context;

    ListView listView;

    AlertDialog alert;
    AlertDialog.Builder dialog;

    List<NameBean> nameBeanList;
    public int checkPosition = 0;

    public PopwindowList(Context context) {
        this.context = context;
        View layout = LayoutInflater.from(context).inflate(R.layout.ui_popwindow_list, null);
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
    }

    private void initView(View layout) {
        listView = layout.findViewById(R.id.popwindow_lv_content);
    }


    public void show() {
        alert.show();
    }

    public void dissmis() {
        if (alert != null) {
            alert.dismiss();
        }
    }

    private MyListAdapter myListAdapter;

    public void setContentData(List<NameBean> data) {
        this.nameBeanList = data;
        myListAdapter = new MyListAdapter();
        listView.setAdapter(myListAdapter);
        View childAt1 = listView.getChildAt(0);
        int diff = childAt1 == null ? 0 : childAt1.getTop();
        listView.setSelectionFromTop(checkPosition, diff);
    }

    public void setItemClickListener(AdapterView.OnItemClickListener mitemClickListener) {
        listView.setOnItemClickListener(mitemClickListener);
    }


    class MyListAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return nameBeanList.size();
        }

        @Override
        public Object getItem(int position) {
            return nameBeanList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHoleder viewHoleder = null;
            if (convertView == null) {
                convertView = View.inflate(context, R.layout.ui_popwindow_item, null);
                viewHoleder = new ViewHoleder();
                viewHoleder.popwindow_list_tv_name = convertView.findViewById(R.id.popwindow_list_tv_name);
                viewHoleder.popwindow_list_iv_check = convertView.findViewById(R.id.popwindow_list_iv_check);
                convertView.setTag(viewHoleder);
            } else {
                viewHoleder = (ViewHoleder) convertView.getTag();
            }
            viewHoleder.popwindow_list_tv_name.setText(nameBeanList.get(position).getName());
            if (position == checkPosition) {
                viewHoleder.popwindow_list_iv_check.setImageResource(R.drawable.check);
            } else {
                viewHoleder.popwindow_list_iv_check.setImageResource(R.drawable.uncheck);

            }

            return convertView;
        }

    }

    static class ViewHoleder {
        TextView popwindow_list_tv_name;
        ImageView popwindow_list_iv_check;

    }
}
