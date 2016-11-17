package bbu.com.smartoffice.base;

import bbu.com.smartoffice.utils.Tutil;

/**
 * Created by G on 2016/11/17 0017.
 */

public abstract class BaseModel<T extends BasePresenter> {
    protected T p;
    protected boolean isActive = true;

    public BaseModel() {
        p = Tutil.getT(this, 0);
    }

    protected abstract void onStart();

    protected void onDestroy() {
        isActive = false;
        p = null;
    }
}
