package com.hua.a02viewpagersystem;

import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ViewPager main_vp;
    private TextView vp_title;
    private LinearLayout vp_ll_radio;
    private ArrayList<ImageView> imageViews;

    private final int[] imgs = {
            R.drawable.a, R.drawable.b, R.drawable.c, R.drawable.d, R.drawable.e
    };
    private final String[] titles = {"放飞梦想1",
            "放飞梦想2",
            "放飞梦想3",
            "放飞梦想4",
            "放飞梦想5",};

    private int prePosition = 0;
    private boolean isSlide = false;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            int i = main_vp.getCurrentItem() + 1;
            main_vp.setCurrentItem(i);
            handler.sendEmptyMessageDelayed(111, 3000);

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        initData();
        handler.sendEmptyMessageDelayed(111, 3000);

    }

    private void initData() {
        imageViews = new ArrayList<>();
        for (int i = 0; i < imgs.length; i++) {
            ImageView imageView = new ImageView(this);
            imageView.setBackgroundResource(imgs[i]);
            imageViews.add(imageView);

            ImageView point = new ImageView(this);
            point.setBackgroundResource(R.drawable.point_selector);
            int pointWidth = DensityUtil.dip2px(this, 8);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(pointWidth, pointWidth);
            if (i == 0) {
                point.setEnabled(true);
            } else {
                point.setEnabled(false);
                params.leftMargin = pointWidth;
            }
            point.setLayoutParams(params);

            vp_ll_radio.addView(point);
        }
        main_vp.addOnPageChangeListener(new MyPageChangeListener());
        main_vp.setAdapter(new MyPagerAdapter());
        int item = Integer.MAX_VALUE / 2 - Integer.MAX_VALUE / 2 % imageViews.size();
        main_vp.setCurrentItem(item);

        vp_title.setText(titles[prePosition]);

    }

    private void initView() {
        main_vp = findViewById(R.id.main_vp);
        vp_title = findViewById(R.id.vp_title);
        vp_ll_radio = findViewById(R.id.vp_ll_radio);
    }

    class MyPageChangeListener implements ViewPager.OnPageChangeListener {
        @Override
        public void onPageScrolled(int i, float v, int i1) {

        }

        @Override
        public void onPageSelected(int i) {
            int realPosition = i % imageViews.size();
            vp_ll_radio.getChildAt(prePosition).setEnabled(false);
            vp_ll_radio.getChildAt(realPosition).setEnabled(true);
            prePosition = realPosition;
            vp_title.setText(titles[realPosition]);
        }

        @Override
        public void onPageScrollStateChanged(int i) {
            if (i == ViewPager.SCROLL_STATE_DRAGGING) {
                isSlide = true;
                handler.removeCallbacksAndMessages(null);
            } else if (i == ViewPager.SCROLL_STATE_SETTLING && isSlide) {

            } else if (i == ViewPager.SCROLL_STATE_IDLE) {
                isSlide = false;
                handler.removeCallbacksAndMessages(null);
                handler.sendEmptyMessageDelayed(111, 3000);
            }
        }
    }


    class MyPagerAdapter extends PagerAdapter {
        @Override
        public int getCount() {
            return Integer.MAX_VALUE;
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
            return view == o;
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            int realPosition = position % imageViews.size();
            final ImageView imageView = imageViews.get(realPosition);
            container.addView(imageView);

            imageView.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    switch (event.getAction()) {
                        case MotionEvent.ACTION_DOWN:
                            handler.removeCallbacksAndMessages(null);
                            break;

                        case MotionEvent.ACTION_UP:
                            handler.removeCallbacksAndMessages(null);
                            handler.sendEmptyMessageDelayed(111, 3000);
                            break;
                    }

                    return false;
                }
            });

            imageView.setTag(realPosition);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int ii = (int) imageView.getTag();
                    String title = titles[ii];
                    Toast.makeText(MainActivity.this, title, Toast.LENGTH_SHORT).show();
                }
            });


            return imageView;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
//            super.destroyItem(container, position, object);
            container.removeView((View) object);
        }
    }
}
