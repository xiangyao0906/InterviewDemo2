package com.example.interviewdemo;

import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

public class HomeRecyclerViewAdapter extends BaseMultiItemQuickAdapter<HomeEntity.ListBean, BaseViewHolder> {

    private GridViewAdapter gridViewAdapter;
    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public HomeRecyclerViewAdapter(List<HomeEntity.ListBean> data) {
        super(data);

        addItemType(0, R.layout.item_one);
        addItemType(2, R.layout.item_two);
        addItemType(1, R.layout.item_three);


    }

    @Override
    protected void convert(BaseViewHolder helper, HomeEntity.ListBean item) {

        switch (helper.getItemViewType()) {

            case 0:

                helper.setText(R.id.title,item.getTitle());
                break;

            case 1:

                helper.setText(R.id.content,item.getTitle());

                Glide.with(mContext).load(item.getPhoto()).into((ImageView) helper.getView(R.id.artical_pic));

                break;
            case 2:

                String photo = item.getPhoto();

                String photo2 = item.getPhoto2();

                String photo3 = item.getPhoto3();

                List<String> pics=new ArrayList<>();
                pics.add(photo);
                pics.add(photo2);
                pics.add(photo3);

                RecyclerView view = helper.getView(R.id.group_divider);

//                LinearLayoutManager ms= new LinearLayoutManager(mContext);
//
//                ms.setOrientation(LinearLayoutManager.HORIZONTAL);
//                view.setLayoutManager(ms);

                SpaceItemDecoration spaceItemDecoration=new SpaceItemDecoration(10);

                view.addItemDecoration(spaceItemDecoration);


                GridLayoutManager layoutManage = new GridLayoutManager(mContext, 3);
                view.setLayoutManager(layoutManage);


                gridViewAdapter=new GridViewAdapter(pics);



                view.setAdapter(gridViewAdapter);


                helper.setText(R.id.title,item.getTitle());
                break;
        }

    }
}
