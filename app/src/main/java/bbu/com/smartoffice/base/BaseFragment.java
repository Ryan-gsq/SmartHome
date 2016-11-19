package bbu.com.smartoffice.base;


import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;

import bbu.com.smartoffice.utils.Tutil;

import static bbu.com.smartoffice.ManageActivity.manageActivity;

/**
 * Created by G on 2016/11/15 0015.
 */

public class BaseFragment<P extends BasePresenter, M extends BaseModel> extends Fragment {
    protected P p;

    /**
     * 获取一个 Fragment实例  如果Fragment之前已经载入 就返回之前的Fragment
     *
     * @param cls
     * @return
     */
    public static <T extends BaseFragment> T getInstance(Class<T> cls) {
        Fragment fragmentByTag = manageActivity.getFragmentManager().findFragmentByTag(cls.getName());
        if (fragmentByTag == null) {
            try {
                fragmentByTag = cls.newInstance();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return (T) fragmentByTag;
    }

    /**
     * 保存一个粉presenter实例 , 通知present调用  onStart
     *
     */
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        p = Tutil.getT(this, 0);
        if (p != null) {
            p.setVM(this, (BaseModel) Tutil.getT(this, 1));
            p.onAttach();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        manageActivity.setTopFragment(this);
    }

    /**
     * 处理返回键的逻辑 ! 如果返回键按下需要自己消费掉这次返回键 返回真
     *
     * @return 真 不响应返回按下 假 响应
     */
    public boolean onInterceptBackClick() {
        manageActivity.getFragmentManager().popBackStack();
        return true;
    }

    /**
     * 清空Present 引用 释放资源
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
        if (p != null)
            p.onDestroy();
    }
}
