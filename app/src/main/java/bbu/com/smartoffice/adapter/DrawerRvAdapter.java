package bbu.com.smartoffice.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import bbu.com.smartoffice.R;
import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * Created by G on 2016/11/16 0016.
 */

public class DrawerRvAdapter extends RecyclerView.Adapter implements View.OnClickListener {

    private final String[] titleList = {"设备列表", "能耗统计"};
    private final int[] iconList = {R.drawable.ic_device_hub_white_48dp, R.drawable.ic_show_chart_white_48dp};
    private int focusItem = 0;
    private int TYPE_NOMAL = 1, TYPE_FOCUS = 2;
    private OnItemClickListener listener;

    public int getFocusItem() {
        return focusItem;
    }

    private void setFocusItem(int index) {
        if (focusItem != index)
            focusItem = index;
    }

    public void setClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onClick(View view) {
        Integer position = (Integer) view.getTag();
        if (position == focusItem)
            return;
        int beforePosition = focusItem;
        setFocusItem(position);
        notifyItemChanged(beforePosition);
        notifyItemChanged(position);
        if (listener != null) {
            listener.onItemClickListener(position);
        }
    }

    //高亮 和 正常
    @Override
    public int getItemViewType(int position) {
        if (position == focusItem)
            return TYPE_FOCUS;
        else
            return TYPE_NOMAL;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rv_drawer, parent, false);
        return new drawerHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        drawerHolder h = (drawerHolder) holder;
        int type = getItemViewType(position);
        if (type == TYPE_FOCUS) {
            h.itemView.setBackgroundResource(R.color.transparentWhite);
            h.endHighlight.setVisibility(View.VISIBLE);
        } else {
            h.itemView.setBackgroundResource(R.drawable.drawer_ripple);
            h.endHighlight.setVisibility(View.GONE);
        }

        h.icon.setImageResource(iconList[position]);
        h.title.setText(titleList[position]);

        h.itemView.setTag(position);
        h.itemView.setOnClickListener(this);
    }

    @Override
    public int getItemCount() {
        return titleList.length;
    }

    public interface OnItemClickListener {
        void onItemClickListener(int position);
    }

    public class drawerHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.icon)
        ImageView icon;
        @Bind(R.id.title)
        TextView title;
        @Bind(R.id.endHighlight)
        TextView endHighlight;
        View itemView;

        public drawerHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            this.itemView = itemView;
        }
    }

}
