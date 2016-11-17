package bbu.com.smartoffice.contract;

import bbu.com.smartoffice.base.BaseModel;
import bbu.com.smartoffice.base.BasePresenter;
import bbu.com.smartoffice.ui.Main;

/**
 * Created by G on 2016/11/16 0016.
 */

public interface MainContract {
    interface MainView {
        void setViewPager();

        void upDate();

        void setError();
    }

    abstract class MainModel extends BaseModel<MainPresenter> {
        public abstract void upDate();

        public abstract void setDeviceState(int id, boolean open);
    }

    abstract class MainPresenter extends BasePresenter<Main, MainModel> {
        public abstract void onSucceedUpData(String data);

        public abstract void onErrorDate(String error);

        public abstract void upDate();


        public abstract void setDeviceState(int id, boolean open);
    }

}
