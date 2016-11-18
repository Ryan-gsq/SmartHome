package bbu.com.smartoffice.jsonBean;

import java.util.List;

/**
 * Created by G on 2016/11/18 0018.
 */

public class StreamBean {
    private int errno = -1;
    private String error;
    private List<DataBean> data;

    public int getErrno() {
        return errno;
    }

    public void setErrno(int errno) {
        this.errno = errno;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * create_time : 2016-11-18 14:12:04
         * update_at : 2016-11-18 14:15:11
         * id : switch
         * uuid : c42633ac-145b-5f69-a539-7fdb63b1c4ec
         * current_value : 0
         */

        private String update_at;
        private String id;
        private String uuid;
        private int current_value;

        public String getUpdate_at() {
            return update_at;
        }

        public void setUpdate_at(String update_at) {
            this.update_at = update_at;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUuid() {
            return uuid;
        }

        public void setUuid(String uuid) {
            this.uuid = uuid;
        }

        public int getCurrent_value() {
            return current_value;
        }

        public void setCurrent_value(int current_value) {
            this.current_value = current_value;
        }
    }
}
