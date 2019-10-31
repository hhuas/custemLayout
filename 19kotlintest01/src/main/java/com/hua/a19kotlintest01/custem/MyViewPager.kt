package com.hua.a19kotlintest01.custem

import android.content.Context
import android.util.AttributeSet
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.ViewGroup
import android.widget.Scroller

class MyViewPager(context: Context?, attrs: AttributeSet?) : ViewGroup(context, attrs) {

    init {
        initView(context)
    }

    /**
     * 手势识别器
     * 1.定义出来
     * 2.实例化-把想要的方法重新
     * 3.在OnTouchEvent()把事件传递给手势识别器
     */
    private lateinit var detector: GestureDetector
    /**
     * 当前页面下标位置
     */
    private var currentIndex: Int = 0

    private lateinit var myScroller: Scroller

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        for (i in 0 until getChildCount()) {
            val childView = getChildAt(i)
            childView.layout(i * width, 0, (i + 1) * width, height)
        }
    }

    private fun initView(context: Context?) {
        myScroller = Scroller(context)

        //2.实例化手势识别器
        detector = GestureDetector(context, object : GestureDetector.SimpleOnGestureListener() {
            override fun onLongPress(e: MotionEvent) {
                super.onLongPress(e)
//                Toast.makeText(context, "onLongPress", Toast.LENGTH_SHORT).show();
            }

            /**
             *
             * @param e1 按下
             * @param e2 抬起
             * @param distanceX 在x轴滑动的距离
             * @param distanceY 在y轴滑动的距离
             * @return
             */
            override fun onScroll(e1: MotionEvent, e2: MotionEvent, distanceX: Float, distanceY: Float): Boolean {
//                return super.onScroll(e1, e2, distanceX, distanceY);
                /**
                 * x:要在X轴移动的距离
                 * y:要在Y轴移动的距离
                 */
                scrollBy(distanceX.toInt(), 0)

                return true
            }

            override fun onDoubleTap(e: MotionEvent): Boolean {
                return super.onDoubleTap(e)
            }
        })
    }

    private var startX: Float = 0.toFloat()

    private var downX: Float = 0.toFloat()
    private var downY: Float = 0.toFloat()

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        super.onInterceptTouchEvent(ev)
        //把事件传递给手势识别器
        detector.onTouchEvent(ev)
        var result = false
        when (ev!!.action) {
            MotionEvent.ACTION_DOWN -> {
                //1.获取坐标
                downX = ev.x
                downY = ev.y
            }
            MotionEvent.ACTION_MOVE -> {
                val endX = ev.x
                val endY = ev.y

                val distanceX = Math.abs(endX - downX)
                val distanceY = Math.abs(endY - downY)
                if (distanceX > distanceY && distanceX > 10) {
                    result = true
                } else {
                    scrollToPager(currentIndex)
                }
            }
            MotionEvent.ACTION_UP -> {
            }
        }
        return result
    }

    /**
     * 屏蔽非法值
     *
     * @param tempIndex
     */
    fun scrollToPager(tempIndex: Int) {
        var tempIndex = tempIndex
        if (tempIndex < 0) {
            tempIndex = 0
        }
        if (tempIndex > childCount - 1) {
            tempIndex = childCount - 1
        }
        //当前页面的下标位置
        currentIndex = tempIndex

        if (mOnPagerChangeListener != null) {
            mOnPagerChangeListener!!.onScrollToPager(currentIndex)
        }

        val distanceX = currentIndex * width - scrollX
        //移动到指定的位置
        //        scrollTo(currentIndex * getWidth(), getScrollY());
        //        myScroller.startScroll(getScrollX(), getScrollY(), distanceX, 0);
        myScroller.startScroll(scrollX, scrollY, distanceX, 0, Math.abs(distanceX))
        invalidate()
    }

    override fun computeScroll() {
        //        super.computeScroll();
        if (myScroller.computeScrollOffset()) {
            val currX = myScroller.currX.toFloat()
            scrollTo(currX.toInt(), 0)
            invalidate()
        }
    }

    /*
     * 监听页面改变
     */
    interface OnPagerChangeListener {
        /**
         * 当页面改变的时候回调这个方法
         *
         * @param position 当前页面的下标
         */
        fun onScrollToPager(position: Int)
    }

    private var mOnPagerChangeListener: OnPagerChangeListener? = null

    fun setOnPagerChangeListener(listener: OnPagerChangeListener) {
        mOnPagerChangeListener = listener
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        for (i in 0 until childCount) {
            val child = getChildAt(i)
            child.measure(widthMeasureSpec, heightMeasureSpec)
        }
    }
}