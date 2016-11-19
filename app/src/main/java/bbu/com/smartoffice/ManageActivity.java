package bbu.com.smartoffice;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.widget.FrameLayout;
import android.widget.Toast;

import bbu.com.smartoffice.base.BaseFragment;
import bbu.com.smartoffice.ui.Drawer;
import bbu.com.smartoffice.ui.Launch;
import butterknife.Bind;
import butterknife.ButterKnife;

import static android.view.View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;
import static android.view.View.SYSTEM_UI_FLAG_LAYOUT_STABLE;

public class ManageActivity extends Activity {

    public static ManageActivity manageActivity;
    @Bind(R.id.DrawerLayout)
    public DrawerLayout drawerLayout;
    @Bind(R.id.MainContain)
    public FrameLayout MainContain;
    @Bind(R.id.DrawerContain)
    public FrameLayout DrawerContain;
    private long backPressPrevious;
    private BaseFragment topFragment;


    public void setTopFragment(BaseFragment topFragment) {
        this.topFragment = topFragment;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        manageActivity = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setFullScreen();
        setDrawer();

        showFragment(BaseFragment.getInstance(Launch.class), false).commitAllowingStateLoss();
    }

    private void setDrawer() {
        getFragmentManager().beginTransaction()
                .replace(R.id.DrawerContain, BaseFragment.getInstance(Drawer.class), Drawer.class.getName())
                .commitAllowingStateLoss();
    }

    /**
     * 窗口状态 全屏
     */
    private void setFullScreen() {
        getWindow()
                .getDecorView()
                .setSystemUiVisibility(
                        SYSTEM_UI_FLAG_LAYOUT_STABLE | SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);

    }

    /**
     * 加入Fragment 是否加入回退栈
     */
    public FragmentTransaction addFragment(BaseFragment instance, boolean backStatic) {
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        String clsName = instance.getClass().getName();
        if (getFragmentManager().findFragmentByTag(clsName) == null)
            fragmentTransaction.add(R.id.MainContain, instance, clsName);
        if (backStatic)
            fragmentTransaction.addToBackStack(clsName);
        return fragmentTransaction;
    }

    /**
     * 显示Fragment 是否加入回退栈
     */
    public FragmentTransaction showFragment(BaseFragment instance, boolean backStatic) {
        FragmentTransaction fragmentTransaction = addFragment(instance, backStatic);
        fragmentTransaction.show(instance);
        return fragmentTransaction;
    }

    public FragmentTransaction replaceFragment(BaseFragment instance) {
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction
                .replace(R.id.MainContain, instance);
        return fragmentTransaction;
    }

    public FragmentTransaction removeFragment(BaseFragment instance) {
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction
                .remove(getFragmentManager().findFragmentByTag(instance.getClass().getName()));
        return fragmentTransaction;
    }

    public void setDrawerLayoutAllow(boolean allow) {
        if (allow) {
            drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED); //打开手势滑
        } else {
            drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED); //关闭
        }
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(Gravity.LEFT)) {
            drawerLayout.closeDrawer(Gravity.LEFT);
            return;
        }

        if (topFragment.onInterceptBackClick()) {
            return;
        }

        if (System.currentTimeMillis() - backPressPrevious < 2000) {
            finish();
        } else {
            Toast.makeText(this, "在按一次退出程序", Toast.LENGTH_SHORT).show();
        }
        backPressPrevious = System.currentTimeMillis();
    }

}
