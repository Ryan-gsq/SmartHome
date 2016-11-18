package bbu.com.smartoffice.Model;

import com.chinamobile.iot.onenet.OneNetApi;
import com.chinamobile.iot.onenet.OneNetError;
import com.chinamobile.iot.onenet.OneNetResponse;
import com.chinamobile.iot.onenet.ResponseListener;

import bbu.com.smartoffice.C;
import bbu.com.smartoffice.contract.MainContract;
import bbu.com.smartoffice.utils.Utils;
import rx.Observable;

/**
 * Created by G on 2016/11/17 0017.
 */

public class MainModel implements MainContract.Model {

    OneNetApi oneNet;

    @Override
    public void onAttach() {
        oneNet = OneNetApi.getInstance(Utils.getContext());
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public Observable<String> getDevices() {
        return Observable.create(subscriber -> {
            oneNet.getDevices(C.APIkey, null, null, null, C.DEVICE, null, null, new ResponseListener() {
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
            });
        });
    }

    @Override
    public Observable<String> getStream(String did) {
        return null;
    }

    @Override
    public Observable<String> setDevice(int id, boolean open) {
        return null;
    }

    @Override
    public Observable<String> queryCmdState(String cmd_uuid) {
        return null;
    }
}
