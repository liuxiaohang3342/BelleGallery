package com.example.lxh.bellegallery.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;

import com.example.lxh.bellegallery.R;

/**
 * Created by lxh on 2016/3/4.
 */
public class MenuDrawerLayout extends LinearLayout {

    private Context mContext;

    public MenuDrawerLayout(Context context) {
        super(context);
        mContext = context;
    }

    public MenuDrawerLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
    }

    @Override
    protected void onVisibilityChanged(View changedView, int visibility) {
        super.onVisibilityChanged(changedView, visibility);
        if (visibility == VISIBLE) {
            openMenu();
        }
    }

    private void openMenu() {
        int size = getChildCount();
        for (int i = 0; i < size; i++) {
            View itemview = getChildAt(i);
            itemview.clearAnimation();
            Animation animation = AnimationUtils.loadAnimation(mContext, R.anim.drawer_open_anim);
            animation.setStartOffset(i * 100);
            animation.setFillAfter(true);
            itemview.startAnimation(animation);
        }
    }

}
