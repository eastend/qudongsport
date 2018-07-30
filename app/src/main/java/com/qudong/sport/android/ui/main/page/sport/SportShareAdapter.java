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

public class SportShareAdapter extends RecyclerView.Adapter {


    private String testUrl = "http://img01.taopic.com/160803/240380-160P31U21189.jpg";
    private String headUrl = "https://b-ssl.duitang.com/uploads/item/201502/14/20150214232932_ECEhu.thumb.700_0.jpeg";

    public SportShareAdapter() {

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ItemHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sport_share, null));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ItemHolder itemHolder = (ItemHolder) holder;

        int innerPadding = (int) DensityUtils.dp2px(10);
        int height = (int) ((DensityUtils.getmScreenWidth() - innerPadding * 2) * 0.5);
        LinearLayout.LayoutParams imgParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, height);
        itemHolder.imgShareCover.setLayoutParams(imgParams);
        Glide.with(itemHolder.itemView.getContext()).load(testUrl).into(itemHolder.imgShareCover);
        Glide.with(itemHolder.itemView.getContext()).load(headUrl).into(itemHolder.imgHead);
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    private class ItemHolder extends RecyclerView.ViewHolder {

        ImageView imgHead, imgShareCover;


        public ItemHolder(View itemView) {
            super(itemView);
            imgHead = itemView.findViewById(R.id.img_head);
            imgShareCover = itemView.findViewById(R.id.img_share_cover);


        }
    }

}
