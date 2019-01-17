package com.smarthomesystem.ju.smarthomesystemapplication.animations;

import android.animation.ValueAnimator;
import android.content.Context;
import android.text.Layout;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.LinearInterpolator;

public class PageTransitions {

    Context mContext;
    Layout mLayout;
    View view;
    int width;
    int height;


    DisplayMetrics displayMetrics = new DisplayMetrics();

    public PageTransitions(Context mContext, View view) {
        this.mContext = mContext;
        this.view = view;
        displayMetrics = mContext.getResources().getDisplayMetrics();
        width = displayMetrics.widthPixels;
        height = displayMetrics.heightPixels;
    }




    public  void pageTransitionBottomToTop(int setDuration){
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(height, 0);


        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {

                float value = (float) animation.getAnimatedValue();

                view.setTranslationY(value);
            }
        });


        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.setDuration(setDuration);
        valueAnimator.start();

    }

    public  void pageTransitionTopToBottom(int setDuration){
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(-height, 0);

        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {

                float value = (float) animation.getAnimatedValue();

                view.setTranslationY(value);
            }
        });

        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.setDuration(setDuration);
        valueAnimator.start();
    }

    public  void pageTransitionLeftToRight(int setDuration){
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(-width, 0);


        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {

                float value = (float) animation.getAnimatedValue();


                view.setTranslationX(value);
            }
        });


        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.setDuration(setDuration);
        valueAnimator.start();
    }


    public  void pageTransitionRightToLeft(int setDuration){
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(width, 0);


        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {

                float value = (float) animation.getAnimatedValue();

                view.setTranslationX(value);
            }
        });

        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.setDuration(setDuration);
        valueAnimator.start();
    }

    public  void pageTransitionSpin(int setDuration){
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, 360);


        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {

                float value = (float) animation.getAnimatedValue();

                view.setRotation(value);
            }
        });

        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.setDuration(setDuration);
        valueAnimator.start();
    }



}
