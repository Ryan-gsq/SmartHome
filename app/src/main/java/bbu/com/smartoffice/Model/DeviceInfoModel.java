package bbu.com.smartoffice.Model;

import com.chinamobile.iot.onenet.OneNetApi;
import com.chinamobile.iot.onenet.OneNetError;
import com.chinamobile.iot.onenet.OneNetResponse;
import com.chinamobile.iot.onenet.ResponseListener;
import com.google.gson.Gson;

import java.util.List;

import bbu.com.smartoffice.C;
import bbu.com.smartoffice.jsonBean.DeviceBean;
import bbu.com.smartoffice.jsonBean.DevicesInfoBean;
import bbu.com.smartoffice.jsonBean.StreamBean;
import bbu.com.smartoffice.utils.ConditionTaskUtil;
import bbu.com.smartoffice.utils.OneNetUtils;
import bbu.com.smartoffice.utils.Utils;
import rx.Observable;
import rx.schedulers.Schedulers;

/**
 * Created by G on 2016/11/17 0017.
 */

/**
 * 用来获取设备列表 +  数据流  通过设置tag 可以获取不同类型的设备
 * 发送命令
 */
public class DeviceInfoModel implements DeviceInfoModelBase {

    public static DevicesInfoBean deviceInfoBean;
    private OneNetApi oneNet;
    private String tag = C.DEVICE;

    @Override
    public void onAttach() {
        oneNet = OneNetApi.getInstance(Utils.getContext());
    }

    @Override
    public void onDestroy() {
        deviceInfoBean = null;
        oneNet = null;
    }

    @Override
    public void setDeviceType(String type) {
        tag = type;
    }

    @Override
    public Observable<Integer> getDevicesInfo() {

        return Observable.create(subscriber -> {
            getDevices().observeOn(Schedulers.io()).subscribe(s -> {
                Gson gson = new Gson();
                DeviceBean deviceBean;
                try {
                    deviceBean = gson.fromJson(s, DeviceBean.class);
                } catch (Exception e) {
                    subscriber.onNext(C.Errno_NoOther);
                    subscriber.onCompleted();
                    return;
                }
                int errno = deviceBean.getErrno();
                if (errno != 0) {
                    //获取到设备错误
                    subscriber.onNext(C.Errno_NoOther);
                    subscriber.onCompleted();
                    return;
                }
                List<bbu.com.smartoffice.jsonBean.DeviceBean.DataBean.DevicesBean> devices = deviceBean.getData().getDevices();
                if (devices == null || devices.size() == 0) {
                    //获取到设备列表为空
                    subscriber.onNext(C.Errno_NoOther);
                    subscriber.onCompleted();
                    return;
                }

                //取回成功   设备列表不为空
                getDevicesInfo(devices).subscribe(dib -> {
                    deviceInfoBean = dib;
                    if (dib == null) {
                        //获取数据流错误
                        subscriber.onNext(C.Errno_NoOther);
                        subscriber.onCompleted();
                        return;
                    }
                    subscriber.onNext(C.Errno_Succeed);
                    subscriber.onCompleted();
                });
            });
        });
    }

    @Override
    public Observable<String> sendDeviceCmd(String did, String cmd) {
        return OneNetUtils.SendCmd(did, cmd);
    }

    @Override
    public Observable<String> queryCmdState(String cmd_uuid) {
        return null;
    }

    private Observable<String> getDevices() {
        return Observable.create(subscriber -> oneNet.getDevices(C.APIkey, null, null, null, tag, null, null, new ResponseListener() {
            @Override
            public void onResponse(OneNetResponse oneNetResponse) {
                subscriber.onNext(oneNetResponse.getRawResponse());
                subscriber.onCompleted();
            }

            @Override
            public void onError(OneNetError oneNetError) {
                subscriber.onNext(oneNetError.getLocalizedMessage());
                subscriber.onCompleted();
            }
        }));
    }

    private Observable<DevicesInfoBean> getDevicesInfo(List<bbu.com.smartoffice.jsonBean.DeviceBean.DataBean.DevicesBean> devices) {
        return Observable.create(subscriber -> {
            int size = devices.size();
            DevicesInfoBean dib = new DevicesInfoBean();
            ConditionTaskUtil conditionTaskUtil = new ConditionTaskUtil(size, () -> {
                subscriber.onNext(dib);
                subscriber.onCompleted();
            });

            Gson gson = new Gson();


            for (int i = 0; i < size; i++) {
                DeviceBean.DataBean.DevicesBean devicesBean = devices.get(i);
                String did = devicesBean.getId();
                getStream(did).subscribe(s -> {
                    StreamBean streamBean;
                    try {
                        streamBean = gson.fromJson(s, StreamBean.class);
                    } catch (Exception e) {
                        streamBean = null;
                        e.printStackTrace();
                    }
                    DevicesInfoBean.infoBean infoBean = new DevicesInfoBean.infoBean();
                    infoBean.setDevicesBean(devicesBean);
                    infoBean.setStreamBean(streamBean);
                    dib.infos.add(infoBean);
                    conditionTaskUtil.excute();
                });
            }
        });
    }

    private Observable<String> getStream(String did) {
        return Observable.create(subscriber -> oneNet.getDatastreams(C.APIkey, did, null, new ResponseListener() {
            @Override
            public void onResponse(OneNetResponse oneNetResponse) {
                subscriber.onNext(oneNetResponse.getRawResponse());
                subscriber.onCompleted();
            }

            @Override
            public void onError(OneNetError oneNetError) {
                subscriber.onNext(oneNetError.getLocalizedMessage());
                subscriber.onCompleted();
            }
        }));
    }
}
