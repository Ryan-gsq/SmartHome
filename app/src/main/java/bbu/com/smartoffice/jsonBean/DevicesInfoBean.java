package bbu.com.smartoffice.jsonBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by G on 2016/11/18 0018.
 */

public class DevicesInfoBean {
    public List<infoBean> infos = new ArrayList<>();

    public static class infoBean {

        public DeviceBean.DataBean.DevicesBean getDevicesBean() {
            return devicesBean;
        }

        public void setDevicesBean(DeviceBean.DataBean.DevicesBean devicesBean) {
            this.devicesBean = devicesBean;
        }

        public StreamBean getStreamBean() {
            return streamBean;
        }

        public void setStreamBean(StreamBean streamBean) {
            this.streamBean = streamBean;
        }

        private DeviceBean.DataBean.DevicesBean devicesBean;
        private StreamBean streamBean;
    }

}
