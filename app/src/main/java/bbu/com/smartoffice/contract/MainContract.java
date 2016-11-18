package bbu.com.smartoffice.contract;

import android.support.v7.widget.RecyclerView;

import java.util.List;

import bbu.com.smartoffice.base.BaseModel;
import bbu.com.smartoffice.base.BasePresenter;
import bbu.com.smartoffice.base.BaseView;
import bbu.com.smartoffice.jsonBean.DrivesBean;
import bbu.com.smartoffice.ui.Main;
import rx.Observable;

/**
 * Created by G on 2016/11/16 0016.
 */

public interface MainContract {
    interface View extends BaseView {
        void setAdapterDate(List<DrivesBean.DataBean.DevicesBean> a);

        RecyclerView.Adapter getAdapter();

        void upDate();

        void setError();
    }

    interface Model extends BaseModel {
        Observable<String> getDevices();

        Observable<String> getStream(String did);

        Observable<String> setDevice(int id, boolean open);

        Observable<String> queryCmdState(String cmd_uuid);
    }

    abstract class Presenter extends BasePresenter<Main, Model> {
        public abstract void upDate();

        public abstract void setDeviceState(int id, boolean open);
    }
}
