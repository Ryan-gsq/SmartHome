package bbu.com.smartoffice.Model;

import com.chinamobile.iot.onenet.OneNetApi;
import com.chinamobile.iot.onenet.OneNetError;
import com.chinamobile.iot.onenet.OneNetResponse;
import com.chinamobile.iot.onenet.ResponseListener;

import bbu.com.smartoffice.contract.MainContract;
import bbu.com.smartoffice.utils.Utils;

import static bbu.com.smartoffice.C.APIkey;
import static bbu.com.smartoffice.C.DEVICE;

/**
 * Created by G on 2016/11/17 0017.
 */

public class MainModel extends MainContract.MainModel {
    private OneNetApi oneNet;

    @Override
    protected void onStart() {
        oneNet = OneNetApi.getInstance(Utils.getContext());
    }

    @Override
    public void upDate() {
        oneNet.getDevices(APIkey, null, null, null, DEVICE, null, null, new ResponseListener() {
            @Override
            public void onResponse(OneNetResponse oneNetResponse) {
                if (oneNetResponse.getErrno() == 0) {
                    // 请求成功
                    String data = oneNetResponse.getData();
                    p.onSucceedUpData(data);
                } else {
                    // 连接服务器成功，但请求发生错误
                    String error = oneNetResponse.getError();
                    p.onErrorDate(error);
                }
            }

            @Override
            public void onError(OneNetError oneNetError) {
                //服务器或者网络错误
                p.onErrorDate("网络或者服务器错误");
            }
        });
    }

    @Override
    public void setDeviceState(int id, boolean open) {
        //设置数据流 修改设备状态
    }
}
