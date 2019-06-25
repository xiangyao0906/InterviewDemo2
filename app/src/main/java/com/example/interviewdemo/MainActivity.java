package com.example.interviewdemo;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.engine.impl.GlideEngine;
import com.zhihu.matisse.internal.entity.CaptureStrategy;

import java.io.File;
import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
  private int  REQUEST_CODE_CHOOSE=0x11;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        findViewById(R.id.netWorlk).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, NetWorkActivity.class));
            }
        });

        findViewById(R.id.uploadPic).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Matisse.from(MainActivity.this)
                        .choose(MimeType.ofImage())
                        .countable(true)
                        .capture(true)
                        .captureStrategy(
                                new CaptureStrategy(true, "com.example.interviewdemo.fileprovider","test"))
                        .maxSelectable(1)
                        .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
                        .thumbnailScale(0.85f)
                        .imageEngine(new GlideEngine())
                        .forResult(REQUEST_CODE_CHOOSE);

            }
        });


        findViewById(R.id.loadWebview).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, WebviewActivity.class));
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_CHOOSE && resultCode == RESULT_OK) {

            List<String> strings = Matisse.obtainPathResult(data);


            uploadPic(strings.get(0));
            Log.d("Matisse", "mSelected: " + strings);
        }
    }

    private void uploadPic(String s) {
        OkHttpClient okHttpClient=new OkHttpClient.Builder().build();

        RequestBody fileBody = RequestBody.create(MediaType.parse("image/png"), new File(s));

        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)

                .addFormDataPart("file", "head_image", fileBody)
                .build();

        Request request = new Request.Builder()
                .url("http://console.juyun.tv/tools/upload.php")
                .post(requestBody)
                .build();


        Call call=okHttpClient.newCall(request);

        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.i("xiangyao", "uploadPic:失败 ");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String jsonString = response.body().string();

                final UploadEntity uploadEntity = new Gson().fromJson(jsonString,UploadEntity.class);


                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        Glide.with(MainActivity.this).load(uploadEntity.getResult().getUrl()).into((ImageView) findViewById(R.id.haha));
                    }
                });


            }
        });


    }
}

