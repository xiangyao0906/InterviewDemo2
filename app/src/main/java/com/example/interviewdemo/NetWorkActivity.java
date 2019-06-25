package com.example.interviewdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.gson.Gson;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class NetWorkActivity extends AppCompatActivity {

    private Banner banner;

    RecyclerView recyclerView;

    HomeRecyclerViewAdapter homeRecyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_net_work);


         banner= findViewById(R.id.banner);

        recyclerView= findViewById(R.id.farm_recycler);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));




        getNetData();
    }

    private void getNetData() {

        OkHttpClient okHttpClient=new OkHttpClient.Builder().build();
        Request request=new Request.Builder().url("http://console.juyun.tv/tools/list.json").build();


        Call call=okHttpClient.newCall(request);

        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {


                HomeEntity homeEntity=new Gson().fromJson(response.body().string(),HomeEntity.class);


                List<HomeEntity.ListSlideBean> list_slide = homeEntity.getList_slide();

                final List<HomeEntity.ListBean> list = homeEntity.getList();


                final List<String>titles=new ArrayList<>();
                final List<String>photos=new ArrayList<>();


                for (int i = 0; i < list_slide.size(); i++) {

                    titles.add(list_slide.get(i).getTitle());

                    photos.add(list_slide.get(i).getPhoto());

                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

//设置内置样式，共有六种可以点入方法内逐一体验使用。
                        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
                        //设置图片加载器，图片加载器在下方
                        banner.setImageLoader(new MyLoader());
                        //设置图片网址或地址的集合
                        banner.setImages(photos);
                        //设置轮播的动画效果，内含多种特效，可点入方法内查找后内逐一体验
                        banner.setBannerAnimation(Transformer.Default);
                        //设置轮播图的标题集合
                        banner.setBannerTitles(titles);
                        //设置轮播间隔时间
                        banner.setDelayTime(3000);
                        //设置是否为自动轮播，默认是“是”。
                        banner.isAutoPlay(true);
                        //设置指示器的位置，小点点，左中右。
                        banner.setIndicatorGravity(BannerConfig.RIGHT)
                                .start();

                        recyclerView.addItemDecoration(new DividerItemDecoration(NetWorkActivity.this, DividerItemDecoration.VERTICAL));
                        homeRecyclerViewAdapter=new HomeRecyclerViewAdapter(list);

                        recyclerView.setAdapter(homeRecyclerViewAdapter);



                    }
                });

            }
        });
    }
}
