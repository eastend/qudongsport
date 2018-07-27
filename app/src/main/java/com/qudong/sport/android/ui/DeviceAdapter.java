package com.qudong.sport.android.ui;

import android.bluetooth.BluetoothDevice;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.clj.fastble.data.BleDevice;
import com.qudong.sport.android.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/7/23 0023.
 */

public class DeviceAdapter extends RecyclerView.Adapter {
    private List<BleDevice> list = new ArrayList<>();
    private OnDeviceItemClickListener onDeviceItemClickListener;

    public void setOnDeviceItemClickListener(OnDeviceItemClickListener onDeviceItemClickListener) {
        this.onDeviceItemClickListener = onDeviceItemClickListener;
    }

    public void addDevice(BleDevice device) {
        this.list.add(device);
        notifyDataSetChanged();
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ItemHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_bluetooth_device, null));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final BleDevice device = list.get(position);
        ItemHolder itemHolder = (ItemHolder) holder;
        itemHolder.textView.setText(device.getName() != null ? device.getName() : device.getMac());
        itemHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onDeviceItemClickListener != null) {
                    onDeviceItemClickListener.onItemDeviceClick(device);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public List<BleDevice> getList() {
        return list;
    }

    private class ItemHolder extends RecyclerView.ViewHolder {

        TextView textView;

        public ItemHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textview);
        }
    }

    public interface OnDeviceItemClickListener {
        void onItemDeviceClick(BleDevice device);
    }

}
