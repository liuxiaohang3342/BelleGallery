package com.example.lxh.bellegallery;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.FrameLayout;

import com.example.lxh.bellegallery.fragment.RecommendFragment;
import com.example.lxh.bellegallery.view.MenuDrawerLayout;
import com.example.lxh.bellegallery.view.WaveView;

public class MainActivity extends FragmentActivity implements MenuDrawerController.OnMenuItemClickListener {

    private MenuDrawerLayout mLeftDrawer;

    private DrawerLayout mDrawerLayout;

    private String[] drawers = {"推荐", "娱乐", "搞笑", "游戏", "体育", "社会", "科技", "美图", "汽车", "国际"};

    private MenuDrawerController mController;

    private WaveView mWaveView;

    private FrameLayout mContentBelow;
    private FrameLayout mContentUp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerLayout.setScrimColor(Color.TRANSPARENT);
        mLeftDrawer = (MenuDrawerLayout) findViewById(R.id.left_drawer);
        mContentBelow = (FrameLayout) findViewById(R.id.content_below);
        mContentUp = (FrameLayout) findViewById(R.id.content_up);
        mController = new MenuDrawerController(this, mDrawerLayout, mLeftDrawer, this);
        mController.initMenu(drawers);
        mWaveView = (WaveView) findViewById(R.id.wave_view);
        replaceFragment(0);
    }

    @Override
    public void onItemClick(View view, int position) {
        mController.closeMenu();
        replaceFragment(position);
        setWaveAnimation(view);
    }

    private void replaceFragment(int position) {
        mContentBelow.removeAllViews();
        View view = mContentUp.getChildAt(0);
        if (view != null) {
            mContentUp.removeView(view);
            mContentBelow.addView(view);
        }
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        Fragment fragment = RecommendFragment.newInstance(drawers[position]);
        transaction.replace(R.id.content_up, fragment);
        transaction.commitAllowingStateLoss();
    }

    /**
     * 切换fragment的水波纹动画
     *
     * @param view
     */
    private void setWaveAnimation(View view) {
        mWaveView.setStartX(view.getX());
        mWaveView.setStartY(view.getY());
        mWaveView.setIsClip(true);
        Animator animator = ObjectAnimator.ofFloat(mWaveView, "revealRadius", 0, 1500);
        animator.setDuration(500);
        animator.start();
    }
}
