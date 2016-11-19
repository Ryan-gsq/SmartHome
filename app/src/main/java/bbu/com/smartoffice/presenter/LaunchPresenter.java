package bbu.com.smartoffice.presenter;

import bbu.com.smartoffice.contract.LaunchContract;
import rx.Observable;

/**
 * Created by G on 2016/11/19 0019.
 */

public class LaunchPresenter extends LaunchContract.Presenter {
    @Override
    public void onAttach() {

    }

    @Override
    public Observable<Integer> getDevice(String type) {
        m.setDeviceType(type);
        return m.getDevicesInfo();
    }
}
