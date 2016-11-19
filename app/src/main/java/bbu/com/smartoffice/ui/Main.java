package bbu.com.smartoffice.ui;

import android.app.FragmentTransaction;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.orhanobut.logger.Logger;

import bbu.com.smartoffice.ManageActivity;
import bbu.com.smartoffice.Model.DeviceInfoModel;
import bbu.com.smartoffice.R;
import bbu.com.smartoffice.adapter.DeviceRvAdapter;
import bbu.com.smartoffice.base.BaseFragment;
import bbu.com.smartoffice.contract.MainContract;
import bbu.com.smartoffice.jsonBean.DevicesInfoBean;
import bbu.com.smartoffice.presenter.MainPresenter;
import bbu.com.smartoffice.utils.TransitionUtil;
import butterknife.Bind;
import butterknife.ButterKnife;

import static bbu.com.smartoffice.ManageActivity.manageActivity;

/**
 * Created by G on 2016/11/15 0015.
 */

public class Main extends BaseFragment<MainPresenter, DeviceInfoModel> implements MainContract.View {
    @Bind(R.id.toolbarBg)
    View toolbarBg;
    @Bind(R.id.fab)
    FloatingActionButton fab;
    @Bind(R.id.tbBack)
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

    private DeviceRvAdapter adapter = new DeviceRvAdapter();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, rootView);
        animatorIcon();
        transition();
        initRecycleView();
        setListener();
        return rootView;
    }

    private void transition() {
        setExitTransition(TransitionUtil.getTransition(R.transition.slid_left));
        setReenterTransition(TransitionUtil.getTransition(R.transition.slid_right));
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
        tbNavigation.setOnClickListener(view -> {
            tbNavigation.setImageDrawable(menuAnimated);
            menuAnimated.start();
            new Handler().postDelayed(() -> manageActivity.drawerLayout.openDrawer(Gravity.LEFT), 300L);
        });
        adapter.setOnclickListener(data -> {
            p.sendCmd(data.did, data.status);
            //不进行命令成功检测
        });
        fab.setOnClickListener(view -> {
            WebViewFragment fragment = WebViewFragment.getInstance(WebViewFragment.class);
            FragmentTransaction fragmentTransaction = ManageActivity.manageActivity.showFragment(fragment, true);
            fragmentTransaction.hide(BaseFragment.getInstance(Main.class)).commitAllowingStateLoss();
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
        recycleView.setHasFixedSize(true);
        recycleView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recycleView.setAdapter(adapter);
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
    public void setAdapterDate(DevicesInfoBean infos) {
        adapter.setData(infos);
    }

    @Override
    public RecyclerView.Adapter getAdapter() {
        return adapter;
    }

    @Override
    public void upDate() {
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showTip(String e) {
        Logger.d(e);
        //TODO 错误提示控件
    }

}

