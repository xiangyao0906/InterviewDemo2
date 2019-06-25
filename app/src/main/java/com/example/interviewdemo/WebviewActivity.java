package com.example.interviewdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

public class WebviewActivity extends AppCompatActivity {

    private String imageUrls[]=new String[]{};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);


        WebView viewById = findViewById(R.id.webview);
        viewById.getSettings().setJavaScriptEnabled(true);
        viewById.addJavascriptInterface(new GetPicFromHtmlJavascriptInterface(this,imageUrls), "imagelistener");
        viewById.loadUrl("http://app.jsports.cn/a/a/l/content_14733.shtml");
        viewById.setWebViewClient(new MyWebViewClient());
    }
}
