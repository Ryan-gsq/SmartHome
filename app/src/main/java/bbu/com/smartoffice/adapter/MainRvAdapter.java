package bbu.com.smartoffice.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.List;

import bbu.com.smartoffice.jsonBean.DrivesBean;

/**
 * Created by G on 2016/11/18 0018.
 */

public class MainRvAdapter extends RecyclerView.Adapter {

    List<DrivesBean.DataBean.DevicesBean> drives;

    public void setData(List<DrivesBean.DataBean.DevicesBean> drivesBean) {
        drives = drivesBean;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return drives == null ? 0 : drives.size();
    }
}
