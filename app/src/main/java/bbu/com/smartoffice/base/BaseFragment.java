package bbu.com.smartoffice.base;


import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;

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
    public void onAttach(Context context) {
        super.onAttach(context);
        p = Tutil.getT(this, 0);
        if (p != null) {
            p.setVM(this, (BaseModel) Tutil.getT(this, 1));
            p.onAttach();
        }
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
