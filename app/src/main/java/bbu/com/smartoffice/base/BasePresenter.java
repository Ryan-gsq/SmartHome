package bbu.com.smartoffice.base;


import bbu.com.smartoffice.utils.Tutil;

/**
 * Created by G on 2016/6/18 0018.
 */

public abstract class BasePresenter<V extends BaseFragment, M extends BaseModel> {
    //dispatchPresent
    protected M m;
    protected V v;

    /**
     * 保存 Model View 实例
     */
    public BasePresenter() {
        v = Tutil.getT(this, 0);
        m = Tutil.getT(this, 1);
    }

    /**
     * 通知 Model 调用 onStart
     */
    protected void onStart() {
        if (m != null)
            m.onStart();
    }

    ;

    /**
     * 释放引用
     */
    protected void onDestroy() {
        v = null;
        if (m != null)
            m.onDestroy();
        m = null;
    }
}
