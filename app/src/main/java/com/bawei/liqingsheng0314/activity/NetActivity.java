package com.bawei.liqingsheng0314.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.bawei.liqingsheng0314.R;
import com.bawei.liqingsheng0314.base.BaseActivity;

public class NetActivity extends BaseActivity {

    private WebView webView;

    @Override
    protected void initData() {
        //获取传递的数据
        Intent intent = getIntent();
        String key = intent.getStringExtra("key");
        //使用web展示
        webView.loadUrl(key);

    }

    @Override
    protected void initView() {
        webView = findViewById(R.id.web);
        //设置支持js
        webView.getSettings().setJavaScriptEnabled(true);
        //监听页面开始
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                Log.e("TAG","页面开始加载");
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                Log.e("TAG","页面加载完成");
            }
        });
        //监听进度
        webView.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                Log.e("TAG","加载进度"+newProgress+"%");
            }

            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                Log.e("TAG","页面标题"+title);
            }
        });
    }

    @Override
    protected int layouId() {
        return R.layout.activity_net;
    }
}
