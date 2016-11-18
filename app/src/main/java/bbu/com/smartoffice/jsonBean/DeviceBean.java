package bbu.com.smartoffice.jsonBean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by G on 2016/11/18 0018.
 */

public class DeviceBean {

    /**
     * errno : 0
     * data : {"per_page":30,"devices":[{"other":{"version":"1.0"},"protocol":"MQTT","create_time":"2016-11-17 22:08:39","online":false,"auth_info":"1244","id":"4069468","title":"树莓派","desc":"树莓派","tags":["SMP"]},{"other":{"version":"1.0"},"private":false,"protocol":"MQTT","create_time":"2016-11-17 22:07:00","online":false,"location":{"lon":0,"lat":0},"auth_info":"1242","id":"4069466","title":"风扇","desc":"风扇","tags":["DEVICE","fengshan"]},{"other":{"version":"1.0"},"private":false,"protocol":"MQTT","create_time":"2016-11-17 22:06:15","online":false,"location":{"lon":0,"lat":0},"auth_info":"1240","id":"4069464","title":"Light","desc":"智能灯","tags":["DEVICE","Light"]},{"other":{"version":"1.0"},"private":false,"protocol":"MQTT","create_time":"2016-11-17 22:06:10","online":false,"location":{"lon":0,"lat":0},"auth_info":"1239","id":"4069463","title":"Light","desc":"智能灯","tags":["DEVICE","Light"]},{"other":{"version":"1.0"},"private":false,"protocol":"MQTT","create_time":"2016-11-17 22:06:05","online":false,"location":{"lon":0,"lat":0},"auth_info":"1238","id":"4069462","title":"Light","desc":"智能灯","tags":["DEVICE","Light"]},{"other":{"version":"1.0"},"private":false,"protocol":"MQTT","create_time":"2016-11-17 22:03:45","online":false,"location":{"lon":0,"lat":0},"auth_info":"1237","id":"4069458","title":"红外传感器","desc":"热感","tags":["SENSOR","hongwai"]},{"other":{"version":"1.0"},"private":false,"protocol":"MQTT","create_time":"2016-11-17 22:03:01","online":false,"location":{"lon":0,"lat":0},"auth_info":"1236","id":"4069457","title":"温度传感器","desc":"温度","tags":["SENSOR","wendu"]},{"protocol":"MQTT","other":{"version":"1.0"},"create_time":"2016-11-17 22:02:44","online":false,"id":"4069456","auth_info":"1235","title":"声音传感器","desc":"声音","tags":["SENSOR"]}],"total_count":8,"page":1}
     * error : succ
     */

    private int errno;
    private DataBean data;
    private String error;

    public int getErrno() {
        return errno;
    }

    public void setErrno(int errno) {
        this.errno = errno;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public static class DataBean {
        /**
         * per_page : 30
         * devices : [{"other":{"version":"1.0"},"protocol":"MQTT","create_time":"2016-11-17 22:08:39","online":false,"auth_info":"1244","id":"4069468","title":"树莓派","desc":"树莓派","tags":["SMP"]},{"other":{"version":"1.0"},"private":false,"protocol":"MQTT","create_time":"2016-11-17 22:07:00","online":false,"location":{"lon":0,"lat":0},"auth_info":"1242","id":"4069466","title":"风扇","desc":"风扇","tags":["DEVICE","fengshan"]},{"other":{"version":"1.0"},"private":false,"protocol":"MQTT","create_time":"2016-11-17 22:06:15","online":false,"location":{"lon":0,"lat":0},"auth_info":"1240","id":"4069464","title":"Light","desc":"智能灯","tags":["DEVICE","Light"]},{"other":{"version":"1.0"},"private":false,"protocol":"MQTT","create_time":"2016-11-17 22:06:10","online":false,"location":{"lon":0,"lat":0},"auth_info":"1239","id":"4069463","title":"Light","desc":"智能灯","tags":["DEVICE","Light"]},{"other":{"version":"1.0"},"private":false,"protocol":"MQTT","create_time":"2016-11-17 22:06:05","online":false,"location":{"lon":0,"lat":0},"auth_info":"1238","id":"4069462","title":"Light","desc":"智能灯","tags":["DEVICE","Light"]},{"other":{"version":"1.0"},"private":false,"protocol":"MQTT","create_time":"2016-11-17 22:03:45","online":false,"location":{"lon":0,"lat":0},"auth_info":"1237","id":"4069458","title":"红外传感器","desc":"热感","tags":["SENSOR","hongwai"]},{"other":{"version":"1.0"},"private":false,"protocol":"MQTT","create_time":"2016-11-17 22:03:01","online":false,"location":{"lon":0,"lat":0},"auth_info":"1236","id":"4069457","title":"温度传感器","desc":"温度","tags":["SENSOR","wendu"]},{"protocol":"MQTT","other":{"version":"1.0"},"create_time":"2016-11-17 22:02:44","online":false,"id":"4069456","auth_info":"1235","title":"声音传感器","desc":"声音","tags":["SENSOR"]}]
         * total_count : 8
         * page : 1
         */

        private List<DevicesBean> devices;

        public List<DevicesBean> getDevices() {
            return devices;
        }

        public void setDevices(List<DevicesBean> devices) {
            this.devices = devices;
        }

        public static class DevicesBean {
            /**
             * other : {"version":"1.0"}
             * protocol : MQTT
             * create_time : 2016-11-17 22:08:39
             * online : false
             * auth_info : 1244
             * id : 4069468
             * title : 树莓派
             * desc : 树莓派
             * tags : ["SMP"]
             * private : false
             * location : {"lon":0,"lat":0}
             */

            private OtherBean other;
            private boolean online;
            private String auth_info;
            private String id;
            private String title;
            private String desc;
            @SerializedName("private")
            private boolean privateX;
            private List<String> tags;

            public OtherBean getOther() {
                return other;
            }

            public void setOther(OtherBean other) {
                this.other = other;
            }

            public boolean isOnline() {
                return online;
            }

            public void setOnline(boolean online) {
                this.online = online;
            }

            public String getAuth_info() {
                return auth_info;
            }

            public void setAuth_info(String auth_info) {
                this.auth_info = auth_info;
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

            public boolean isPrivateX() {
                return privateX;
            }

            public void setPrivateX(boolean privateX) {
                this.privateX = privateX;
            }

            public List<String> getTags() {
                return tags;
            }

            public void setTags(List<String> tags) {
                this.tags = tags;
            }

            public static class OtherBean {
                /**
                 * version : 1.0
                 */

                private String version;

                public String getVersion() {
                    return version;
                }

                public void setVersion(String version) {
                    this.version = version;
                }
            }
        }
    }
}
