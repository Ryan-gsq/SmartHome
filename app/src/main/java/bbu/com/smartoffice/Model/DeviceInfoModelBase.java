package bbu.com.smartoffice.Model;

import bbu.com.smartoffice.base.BaseModel;
import rx.Observable;

/**
 * Created by G on 2016/11/18 0018.
 */

public interface DeviceInfoModelBase extends BaseModel {
    void setDeviceType(String type);

    Observable<Integer> getDevicesInfo();

    Observable<String> sendDeviceCmd(String id, String cmd);

    //TODO 命令返回状态查询
    Observable<String> queryCmdState(String cmd_uuid);
}
