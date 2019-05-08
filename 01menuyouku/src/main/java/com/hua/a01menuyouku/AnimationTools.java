package com.hua.a01menuyouku;

import android.animation.ObjectAnimator;
import android.view.ViewGroup;
import android.view.animation.RotateAnimation;

public class AnimationTools {

    public static void hideView(ViewGroup viewGroup) {
        hideView(viewGroup, 0);
    }

    public static void showView(ViewGroup viewGroup) {
        showView(viewGroup, 0);
    }

    public static void hideView(ViewGroup viewGroup, int startOff) {
//                                                               RotateAnimation
        ObjectAnimator animator = ObjectAnimator.ofFloat(viewGroup, "rotation", 0, 180);
        animator.setDuration(2000);
        animator.setStartDelay(startOff);
        animator.start();
        viewGroup.setPivotX(viewGroup.getWidth() / 2);
        viewGroup.setPivotY(viewGroup.getHeight());
    }

    public static void showView(ViewGroup viewGroup, int startOff) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(viewGroup, "rotation", 180, 360);
        animator.setDuration(2000);
        animator.setStartDelay(startOff);
        animator.start();
        viewGroup.setPivotX(viewGroup.getWidth() / 2);
        viewGroup.setPivotY(viewGroup.getHeight());
    }


}
