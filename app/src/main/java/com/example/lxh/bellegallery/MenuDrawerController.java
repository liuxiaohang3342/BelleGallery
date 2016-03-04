package com.example.lxh.bellegallery;

import android.content.Context;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.lxh.bellegallery.R;

/**
 * Created by lxh on 2016/3/4.
 */
public class MenuDrawerController {

    private DrawerLayout mDrawerLayout;

    private LinearLayout mMenuLayout;

    private Context mContext;

    private OnMenuItemClickListener mListener;


    public MenuDrawerController(Context context, DrawerLayout drawerLayout, LinearLayout menuLayout, OnMenuItemClickListener listener) {
        mContext = context;
        mDrawerLayout = drawerLayout;
        mMenuLayout = menuLayout;
        mListener = listener;
    }

    public void initMenu(String[] drawers) {
        for (int i = 0; i < drawers.length; i++) {
            final TextView view = (TextView) LayoutInflater.from(mContext).inflate(R.layout.menu_item, null);
            view.setText(drawers[i]);
            mMenuLayout.addView(view);
            final int index = i;
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null) {
                        mListener.onItemClick(view, index);
                    }
                }
            });
        }
    }


    /**
     * 带动画效果的关闭
     */
    public void closeMenu() {
        int size = mMenuLayout.getChildCount();
        for (int i = 0; i < size; i++) {
            View itemview = mMenuLayout.getChildAt(i);
            itemview.clearAnimation();
            Animation animation = AnimationUtils.loadAnimation(mContext, R.anim.drawer_close_anim);
            if (i == size - 1) {
                animation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        mDrawerLayout.closeDrawers();
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {
                    }
                });
            }
            animation.setStartOffset(i * 100);
            animation.setFillAfter(true);
            itemview.startAnimation(animation);
        }
    }


    public interface OnMenuItemClickListener {
        void onItemClick(View view, int position);
    }

}
