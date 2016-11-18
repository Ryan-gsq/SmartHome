package bbu.com.smartoffice.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import bbu.com.smartoffice.R;
import bbu.com.smartoffice.jsonBean.DeviceBean;
import bbu.com.smartoffice.jsonBean.DevicesInfoBean;
import bbu.com.smartoffice.jsonBean.StreamBean;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by G on 2016/11/18 0018.
 */

public class DeviceRvAdapter extends RecyclerView.Adapter {

    DevicesInfoBean devices;

    public void setData(DevicesInfoBean drivesBean) {
        devices = drivesBean;
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
        setIcon(h, infoBean);
        DeviceBean.DataBean.DevicesBean devicesBean = infoBean.getDevicesBean();
        StreamBean streamBean = infoBean.getStreamBean();
        StreamBean.DataBean dataBean;
        int currentValue = 0;
        for (int i = 0; i < streamBean.getData().size(); i++) {
            dataBean = streamBean.getData().get(i);
            if (dataBean.getId().equals("switch")) {
                currentValue = dataBean.getCurrent_value();
                break;
            }
        }
        h.title.setText(devicesBean.getTitle());
        h.energy.setText("25Kw/H");
    }

    private String getTimeDifferent(String s, String e) {
        SimpleDateFormat dfs = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        java.util.Date begin = null;
        java.util.Date end = null;
        long between = 0;
        try {
            begin = dfs.parse(s);
            end = dfs.parse(e);
            between = (end.getTime() - begin.getTime()) / 1000;//除以1000是为了转换成秒
        } catch (ParseException ex) {
            ex.printStackTrace();
        }

        long day1 = between / (24 * 3600);
        long hour1 = between % (24 * 3600) / 3600;
        long minute1 = between % 3600 / 60;
        long second1 = between % 60 / 60;

        String result = "";
        if (day1 != 0)
            result += day1 + " 天 ";
        if (hour1 != 0)
            result += hour1 + " 小时 ";
        if (minute1 != 0)
            result += minute1 + " 分 ";
        if (second1 != 0)
            result += second1 + " 秒 ";

        return result;
    }


    private void setIcon(DeviceHold h, DevicesInfoBean.infoBean infoBean) {

    }

    @Override
    public int getItemCount() {
        return (devices == null || devices.infos == null) ? 0 : devices.infos.size();
    }

    private static class DeviceHold extends RecyclerView.ViewHolder {
        @Bind(R.id.icon)
        ImageView icon;
        @Bind(R.id.title)
        TextView title;
        @Bind(R.id.energy)
        TextView energy;
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
