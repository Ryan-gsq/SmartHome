package bbu.com.smartoffice.utils;

import bbu.com.smartoffice.C;
import bbu.com.smartoffice.net.RestfulClient;
import rx.Observable;

import static bbu.com.smartoffice.net.RestfulClient.byteToString;

/**
 * Created by G on 2016/11/18 0018.
 */

public class OneNetUtils {
    public static Observable<String> SendCmd(String did, String cmd) {
        RestfulClient restfulClient = new RestfulClient();
        restfulClient.addUrlParams("device_id", did);
        restfulClient.addHeaderParams("api-key", C.APIkey);
        restfulClient.addBody(cmd);
        return Observable.create(subscriber -> restfulClient.create(C.POST, C.OneNetCMDUrl, (result, code, header) -> {
            String toString;
            if (code == 200) {
                toString = byteToString(result);
            } else {
                toString = "CODE:" + code + "  服务器错误 或者 网络错误";
            }
            subscriber.onNext(toString);
            subscriber.onCompleted();
        }));
    }
}
