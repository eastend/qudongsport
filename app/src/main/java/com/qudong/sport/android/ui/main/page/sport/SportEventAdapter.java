package com.qudong.sport.android.ui.main.page.sport;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.qudong.sport.android.R;
import com.sport.qudong.baselibrary.utils.DensityUtils;

/**
 * Created by Administrator on 2018/7/29 0029.
 */

public class SportEventAdapter extends RecyclerView.Adapter {

//    private final RequestOptions requestOptions;

    private String testUrl = "http://img01.taopic.com/160803/240380-160P31U21189.jpg";

    public SportEventAdapter() {

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ItemHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sport_event, null));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ItemHolder itemHolder = (ItemHolder) holder;

        int innerPadding = (int) DensityUtils.dp2px(10);
        int height = (int) ((DensityUtils.getmScreenWidth() - innerPadding * 2) * 0.6);
        FrameLayout.LayoutParams imgParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, height);
        itemHolder.imgCover.setLayoutParams(imgParams);



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
