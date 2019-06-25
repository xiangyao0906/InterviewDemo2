package com.example.interviewdemo;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

public class GridViewAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public GridViewAdapter( @Nullable List<String> data) {
        super(R.layout.grid_view_item, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {

        Glide.with(mContext).load(item).into((ImageView) helper.getView(R.id.gridViewItemPic));

    }
}
