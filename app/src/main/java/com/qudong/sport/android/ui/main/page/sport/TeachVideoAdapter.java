package com.qudong.sport.android.ui.main.page.sport;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.qudong.sport.android.R;
import com.sport.qudong.baselibrary.utils.DensityUtils;

/**
 * Created by Administrator on 2018/7/29 0029.
 */

public class TeachVideoAdapter extends RecyclerView.Adapter {

//    private final RequestOptions requestOptions;

    private String testUrl = "http://img01.taopic.com/160803/240380-160P31U21189.jpg";

    public TeachVideoAdapter() {

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ItemHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_teach_video, null));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ItemHolder itemHolder = (ItemHolder) holder;

        int width = (int) (DensityUtils.getmScreenWidth() * 0.6);
        int height = (int) (width / 1.8);
        LinearLayout.LayoutParams imgParams = new LinearLayout.LayoutParams(width, height);
        itemHolder.imgCover.setLayoutParams(imgParams);

        int margin = (int) DensityUtils.dp2px(15);

        RecyclerView.LayoutParams params = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(margin, (int) (margin * 1.5), 0, (int) (margin * 1.5));
        itemHolder.itemView.setLayoutParams(params);

        Glide.with(itemHolder.itemView.getContext()).load(testUrl).into(itemHolder.imgCover);
    }

    @Override
    public int getItemCount() {
        return 5;
    }

    private class ItemHolder extends RecyclerView.ViewHolder {

        ImageView imgCover;
        TextView tvTitle;
        TextView tvDesc;

        public ItemHolder(View itemView) {
            super(itemView);

            imgCover = itemView.findViewById(R.id.img_cover);

            tvTitle = itemView.findViewById(R.id.tv_title);

            tvDesc = itemView.findViewById(R.id.tv_desc);


        }
    }

}
