package com.hua.a10indexview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class IndexView extends View {
    private int size_zm_height;
    private int word_select_color;
    private int word_unselect_color;

    private String[] words = {"A", "B", "C", "D", "E", "F", "G", "H", "I",
            "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
            "W", "X", "Y", "Z"};
    private Context context;
    private Paint paint;
    /**
     * 每条的宽和高
     */
    private int itemWidth;
    private int itemHeight;

    private int touchIndex = -1;

    public IndexView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.IndexView);
        for (int i = 0; i < typedArray.getIndexCount(); i++) {
            int index = typedArray.getIndex(i);
            switch (index) {
                case R.styleable.IndexView_size_zm_height:
                    size_zm_height = typedArray.getInt(index, 20);
                    break;
                case R.styleable.IndexView_word_select_color:
                    word_select_color = typedArray.getColor(index, Color.RED);
                    break;
                case R.styleable.IndexView_word_unselect_color:
                    word_unselect_color = typedArray.getColor(index, Color.GRAY);
                    break;
            }
        }

        typedArray.recycle();

        paint = new Paint();
//        paint.setColor(Color.RED);
        paint.setColor(word_select_color);
        paint.setAntiAlias(true);
        paint.setTypeface(Typeface.DEFAULT_BOLD); //设置粗体
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        itemWidth = getMeasuredWidth();
        itemHeight = getMeasuredHeight() / words.length;

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (int i = 0; i < words.length; i++) {
            if (i == touchIndex) {
//                paint.setColor(Color.GRAY);
                paint.setColor(word_unselect_color);
            } else {
//                paint.setColor(Color.RED);
                paint.setColor(word_select_color);
            }
            if (mOnIndexChangeListener != null && touchIndex < words.length && touchIndex > -1) {
                mOnIndexChangeListener.onIndexChanger(words[touchIndex]);
            } else {
                mOnIndexChangeListener.onIndexChanger(null);
            }


            String word = words[i];
            Rect rect = new Rect();
            paint.getTextBounds(word, 0, 1, rect);
            paint.setTextSize(DensityUtil.dip2px(context, size_zm_height));

            int wordWidth = rect.width();
            int wordHeight = rect.height();
//            float wordX = itemWidth / 2 - wordWidth / 2;
            float wordX = itemWidth / 2 - wordWidth / 6;
            float wordY = itemHeight / 2 + wordHeight / 2 + itemHeight * i;
            //绘制
            canvas.drawText(word, wordX, wordY, paint);

        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                touchIndex = (int) (event.getY() / itemHeight);
                invalidate();
                break;
            case MotionEvent.ACTION_MOVE:
                int index = (int) (event.getY() / itemHeight);
                if (index != touchIndex) {
                    touchIndex = index;
                    invalidate();
                }
                break;
            case MotionEvent.ACTION_UP:
                touchIndex = -1;
                invalidate();
                break;
        }

        return true;
    }

    public interface OnIndexChangeListener {
        void onIndexChanger(String word);
    }

    private OnIndexChangeListener mOnIndexChangeListener;

    public void setOnIndexChangeListener(OnIndexChangeListener mOnIndexChangeListener) {
        this.mOnIndexChangeListener = mOnIndexChangeListener;
    }
}
