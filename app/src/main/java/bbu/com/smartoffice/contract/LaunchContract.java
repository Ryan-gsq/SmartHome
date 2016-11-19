package bbu.com.smartoffice.contract;

import bbu.com.smartoffice.Model.DeviceInfoModelBase;
import bbu.com.smartoffice.base.BasePresenter;
import bbu.com.smartoffice.base.BaseView;
import rx.Observable;

/**
 * Created by G on 2016/11/19 0019.
 */

public interface LaunchContract {
    interface View extends BaseView {
    }

    abstract class Presenter extends BasePresenter<View, DeviceInfoModelBase> {
        public abstract Observable<Integer> getDevice(String type);
    }

}
