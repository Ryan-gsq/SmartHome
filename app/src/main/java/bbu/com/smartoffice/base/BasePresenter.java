package bbu.com.smartoffice.base;


/**
 * Created by G on 2016/6/18 0018.
 */

public abstract class BasePresenter<V, M extends BaseModel> {
    //dispatchPresent
    protected M m;
    protected V v;

    public void setVM(V v, M m) {
        this.m = m;
        this.v = v;
        if (m != null)
            m.onAttach();
    }

    public abstract void onAttach();

    /**
     * 释放引用
     */
    public void onDestroy() {
        if (m != null) {
            m.onDestroy();
        }
        v = null;
        m = null;
    }
}
