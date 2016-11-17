package bbu.com.smartoffice.ui;

import android.graphics.drawable.AnimatedVectorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.ogaclejapan.smarttablayout.utils.ViewPagerItemAdapter;

import bbu.com.smartoffice.R;
import bbu.com.smartoffice.base.BaseFragment;
import bbu.com.smartoffice.contract.MainContract;
import butterknife.Bind;
import butterknife.ButterKnife;

import static bbu.com.smartoffice.ManageActivity.manageActivity;

/**
 * Created by G on 2016/11/15 0015.
 */

public class Main extends BaseFragment<MainContract.MainPresenter> implements MainContract.MainView {
    @Bind(R.id.toolbarBg)
    View toolbarBg;
    @Bind(R.id.fab)
    FloatingActionButton fab;
    @Bind(R.id.tbNavigation)
    ImageView tbNavigation;
    @Bind(R.id.tbLogo)
    ImageView tbLogo;
    @Bind(R.id.tbAdd)
    ImageView tbAdd;
    @Bind(R.id.recycleView)
    RecyclerView recycleView;

    private View rootView;
    private AnimatedVectorDrawable menuAnimated;
    private AnimatedVectorDrawable backAnimated;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, rootView);
        animatorIcon();
        setListener();
        initRecycleView();
        return rootView;
    }

    /**
     * 视图监听
     */
    private void setListener() {
        manageActivity.drawerLayout.addDrawerListener(new DrawerLayout.SimpleDrawerListener() {
            @Override
            public void onDrawerOpened(View drawerView) {
                animNavButton(false);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                animNavButton(true);
            }
        });
        tbNavigation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tbNavigation.setImageDrawable(menuAnimated);
                menuAnimated.start();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        manageActivity.drawerLayout.openDrawer(Gravity.LEFT);
                    }
                }, 300L);
            }
        });
    }

    /**
     * NavButton 矢量动画
     */
    private void animatorIcon() {
        menuAnimated = (AnimatedVectorDrawable) getActivity().getDrawable(R.drawable.ic_menu_animatable);
        backAnimated = (AnimatedVectorDrawable) getActivity().getDrawable(R.drawable.ic_back_animatable);
    }

    /**
     * NavButton 矢量动画 改变   back - true  menu>back
     */
    private void animNavButton(boolean back) {
        if (back) {
            tbNavigation.setImageDrawable(menuAnimated);
            menuAnimated.start();
        } else {
            tbNavigation.setImageDrawable(backAnimated);
            backAnimated.start();
        }
    }

    /**
     * 初始化 recycleView
     */
    private void initRecycleView() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    /**
     * View 接口实现
     * -------------------------------------------------
     */
    @Override
    public void setViewPager() {

    }

    @Override
    public void upDate() {

    }

    @Override
    public void setError() {

    }

    /**
     * viewPager 改变监听
     */
    private class GOnPageChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }

    }
}

