package bbu.com.smartoffice.presenter;

import bbu.com.smartoffice.C;
import bbu.com.smartoffice.Model.DeviceInfoModel;
import bbu.com.smartoffice.contract.MainContract;
import bbu.com.smartoffice.utils.OneNetUtils;
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
                case C.Errno_Succeed:
                    tip = "更新设备数据成功";
                    break;
            }
            v.showTip(tip);

            if (s.equals(C.Errno_Succeed)) {
                v.setAdapterDate(DeviceInfoModel.deviceInfoBean);
                v.upDate();
            }
        });

    }

    @Override
    public void sendCmd(String did, String state) {
        OneNetUtils.SendCmd(did, state).subscribe(s -> com.orhanobut.logger.Logger.d(s));
    }

    @Override
    public void onAttach() {
        m.setDeviceType(C.DEVICE);
        if (DeviceInfoModel.deviceInfoBean != null)
            v.setAdapterDate(DeviceInfoModel.deviceInfoBean);
        else
            upDate();
    }
}
