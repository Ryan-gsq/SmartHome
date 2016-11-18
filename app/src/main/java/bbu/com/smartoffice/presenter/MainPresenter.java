package bbu.com.smartoffice.presenter;

import com.orhanobut.logger.Logger;

import bbu.com.smartoffice.contract.MainContract;
import rx.functions.Action1;


/**
 * Created by G on 2016/11/17 0017.
 */

public class MainPresenter extends MainContract.Presenter {



    @Override
    public void upDate() {
        m.getDevices().subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                Logger.d(s);
            }
        });
    }

    @Override
    public void setDeviceState(int id, boolean open) {

    }

    @Override
    public void onAttach() {
        upDate();
    }
}
