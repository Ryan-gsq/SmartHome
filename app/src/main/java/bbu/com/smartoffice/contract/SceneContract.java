package bbu.com.smartoffice.contract;

import bbu.com.smartoffice.Model.DeviceInfoModelBase;
import bbu.com.smartoffice.base.BasePresenter;
import bbu.com.smartoffice.base.BaseView;

/**
 * Created by G on 2016/11/19 0019.
 */

public interface SceneContract {
    interface View extends BaseView {
    }

    abstract class Presenter extends BasePresenter<View, DeviceInfoModelBase> {
    }

}
