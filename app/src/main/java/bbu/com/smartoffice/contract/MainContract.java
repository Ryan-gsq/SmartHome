package bbu.com.smartoffice.contract;

import android.support.v7.widget.RecyclerView;

import java.util.List;

import bbu.com.smartoffice.Model.DeviceInfoModelBase;
import bbu.com.smartoffice.base.BasePresenter;
import bbu.com.smartoffice.base.BaseView;
import bbu.com.smartoffice.jsonBean.DeviceBean;
import bbu.com.smartoffice.ui.Main;

/**
 * Created by G on 2016/11/16 0016.
 */

public interface MainContract {
    interface View extends BaseView {
        void setAdapterDate(List<DeviceBean.DataBean.DevicesBean> a);

        RecyclerView.Adapter getAdapter();

        void upDate();

        void showTip(String e);
    }

    abstract class Presenter extends BasePresenter<Main, DeviceInfoModelBase> {
        public abstract void upDate();

        public abstract void setDeviceState(int did, boolean open);
    }
}
