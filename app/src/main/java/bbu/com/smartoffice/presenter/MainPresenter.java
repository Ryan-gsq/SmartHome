package bbu.com.smartoffice.presenter;

import bbu.com.smartoffice.C;
import bbu.com.smartoffice.contract.MainContract;
import rx.android.schedulers.AndroidSchedulers;


/**
 * Created by G on 2016/11/17 0017.
 */

public class MainPresenter extends MainContract.Presenter {

    @Override
    public void upDate() {
        m.getDevicesInfo().observeOn(AndroidSchedulers.mainThread()).subscribe(s -> {
            String tip = "";
            switch (s) {
                case C.Errno_NoDevice:
                    tip = "没有设备 请先添加设备";
                    break;
                case C.Errno_NoOther:
                    tip = "获取数据失败 未知错误";
                    break;
                case C.Errno_NoStream:
                    tip = "获取设备状态失败";
                    break;
            }
            v.showTip(tip);

            if (s == C.Errno_Succeed) {
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
