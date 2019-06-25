package com.example.interviewdemo;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Transition;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class PhotoBrowserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_browser);

        ImageView viewById = findViewById(R.id.singlePic);

        final String curImageUrl = getIntent().getStringExtra("curImageUrl");

        Glide.with(this).load(curImageUrl).into(viewById);

        viewById.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {


                /**
                 * Glide 加载图片保存到本地
                 *
                 * imgUrl 图片地址
                 * imgName 图片名称
                 */
                Glide.with(PhotoBrowserActivity.this).load(curImageUrl).asBitmap().toBytes().into(new SimpleTarget<byte[]>() {
                    @Override
                    public void onResourceReady(byte[] bytes, GlideAnimation<? super byte[]> glideAnimation) {
                        try {
                            savaBitmap("helloWorld.jpg", bytes);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });



                return true;
            }
        });
    }

    // 保存图片到手机指定目录
    public void savaBitmap(String imgName, byte[] bytes) {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            String filePath = null;
            FileOutputStream fos = null;
            try {
                filePath = Environment.getExternalStorageDirectory().getPath() + "/MyImg";
                File imgDir = new File(filePath);
                if (!imgDir.exists()) {
                    imgDir.mkdirs();
                }
                imgName = filePath + "/" + imgName;
                fos = new FileOutputStream(imgName);
                fos.write(bytes);
                Toast.makeText(PhotoBrowserActivity.this, "图片已保存到" + filePath, Toast.LENGTH_SHORT).show();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (fos != null) {
                        fos.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else {
            Toast.makeText(PhotoBrowserActivity.this, "请检查SD卡是否可用", Toast.LENGTH_SHORT).show();
        }
    }

}
