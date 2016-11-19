package bbu.com.smartoffice.jsonBean;

/**
 * Created by G on 2016/11/19 0019.
 */

public class CmdBean {


    private String rulename;
    private ConditionBean condition;
    private ActionBean action;

    public String getRulename() {
        return rulename;
    }

    public void setRulename(String rulename) {
        this.rulename = rulename;
    }

    public ConditionBean getCondition() {
        return condition;
    }

    public void setCondition(ConditionBean condition) {
        this.condition = condition;
    }

    public ActionBean getAction() {
        return action;
    }

    public void setAction(ActionBean action) {
        this.action = action;
    }

    public static class ConditionBean {
        /**
         * device_id : detector
         * device_function :
         * type : >
         * value : 0
         */

        private String device_id;
        private String device_function;
        private String type;
        private int value;

        public String getDevice_id() {
            return device_id;
        }

        public void setDevice_id(String device_id) {
            this.device_id = device_id;
        }

        public String getDevice_function() {
            return device_function;
        }

        public void setDevice_function(String device_function) {
            this.device_function = device_function;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }
    }

    public static class ActionBean {
        /**
         * device_id : leds
         * value : 1
         */

        private String device_id;
        private int value;

        public String getDevice_id() {
            return device_id;
        }

        public void setDevice_id(String device_id) {
            this.device_id = device_id;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }
    }
}
