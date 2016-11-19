package bbu.com.smartoffice.adapter;

import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import bbu.com.smartoffice.R;
import bbu.com.smartoffice.jsonBean.DeviceBean;
import bbu.com.smartoffice.jsonBean.DevicesInfoBean;
import bbu.com.smartoffice.jsonBean.StreamBean;
import bbu.com.smartoffice.utils.Utils;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by G on 2016/11/18 0018.
 */

public class DeviceRvAdapter extends RecyclerView.Adapter {

    DevicesInfoBean devices;
    Map<String, Integer> iconMap = new HashMap<>();
    private onClickListener listener;


    public DeviceRvAdapter() {
    }

    public void setData(DevicesInfoBean drivesBean) {
        devices = drivesBean;
    }

    public void setOnclickListener(onClickListener listener) {
        this.listener = listener;
    }

    public interface onClickListener {
        void onClick(ClickData data);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rv_main, parent, false);
        return new DeviceHold(v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        DeviceHold h = (DeviceHold) holder;
        DevicesInfoBean.infoBean infoBean = devices.infos.get(position);
        DeviceBean.DataBean.DevicesBean devicesBean = infoBean.getDevicesBean();
        StreamBean streamBean = infoBean.getStreamBean();
        StreamBean.DataBean dataBean;
        int currentValue = 0;
        String endTime = "";
        for (int i = 0; i < streamBean.getData().size(); i++) {
            dataBean = streamBean.getData().get(i);
            if (dataBean.getId().equals("switch")) {
                currentValue = dataBean.getCurrent_value();
                endTime = dataBean.getUpdate_at();
                break;
            }
        }

        h.icon.setImageDrawable(getIcon(devicesBean.getTags().get(1)));
        h.title.setText(devicesBean.getTitle());
        h.switchState.setText(devicesBean.isOnline() ? "在线" : "离线");
        h.switchButton.setChecked(currentValue != 0);
        h.time.setText(getTimeDifferent(endTime));
        h.mode.setText("无规则");

        int finalCurrentValue = currentValue;
        h.switchButton.setOnCheckedChangeListener((compoundButton, b) -> {
            ClickData clickData = new ClickData();
            clickData.did = devicesBean.getId();
            clickData.status = finalCurrentValue == 0 ? "on" : "off";
            if (listener != null)
                listener.onClick(clickData);
        });
    }

    public static class ClickData {
        public String did;
        public String status;
    }

    private Drawable getIcon(String tag) {
        Drawable drawable = Utils.getContext().getDrawable(R.drawable.audio_wave);
        drawable.mutate().setColorFilter(Utils.getColorFromRes(R.color.black), PorterDuff.Mode.SRC_IN);
        drawable.setAlpha(137);
        return drawable;
    }


    private String getTimeDifferent(String e) {
        SimpleDateFormat dfs = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date curDate = new Date(System.currentTimeMillis());//获取当前时间
        String s = dfs.format(curDate);
        java.util.Date begin = null;
        java.util.Date end = null;
        long between = 0;
        try {
            begin = dfs.parse(e);
            end = dfs.parse(s);
            between = (end.getTime() - begin.getTime()) / 1000;//除以1000是为了转换成秒
        } catch (Exception ex) {
            return "nothing";
        }

        long day1 = between / (24 * 3600);
        long hour1 = between % (24 * 3600) / 3600;
        long minute1 = between % 3600 / 60;
        long second1 = between % 60 / 60;

        String result = "";
        if (day1 != 0)
            result += day1 + " day ";
        if (hour1 != 0)
            result += hour1 + " hour ";
        if (minute1 != 0)
            result += minute1 + " min ";
        if (second1 != 0)
            result += second1 + " s ";

        if (result.equals(""))
            result = "1min ";

        return result + "ago";
    }


    @Override
    public int getItemCount() {
        return (devices == null || devices.infos == null) ? 0 : devices.infos.size();
    }


    public static class DeviceHold extends RecyclerView.ViewHolder {
        @Bind(R.id.icon)
        ImageView icon;
        @Bind(R.id.title)
        TextView title;
        @Bind(R.id.switchState)
        TextView switchState;
        @Bind(R.id.mode)
        TextView mode;
        @Bind(R.id.time)
        TextView time;
        @Bind(R.id.switchButton)
        CheckBox switchButton;

        DeviceHold(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
