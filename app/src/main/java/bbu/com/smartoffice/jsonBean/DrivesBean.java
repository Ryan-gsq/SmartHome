package bbu.com.smartoffice.jsonBean;

import com.google.gson.annotations.Expose;

import java.util.List;

/**
 * Created by G on 2016/11/18 0018.
 */

public class DrivesBean {

    @Expose
    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        @Expose
        private List<DevicesBean> devices;

        public List<DevicesBean> getDevices() {
            return devices;
        }

        public void setDevices(List<DevicesBean> devices) {
            this.devices = devices;
        }

        public static class DevicesBean {


            @Expose
            private boolean online;
            @Expose
            private String id;
            @Expose
            private String title;
            @Expose
            private String desc;

            public boolean isOnline() {
                return online;
            }

            public void setOnline(boolean online) {
                this.online = online;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getDesc() {
                return desc;
            }

            public void setDesc(String desc) {
                this.desc = desc;
            }

        }
    }
}
