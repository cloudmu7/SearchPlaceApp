package com.cloud7mu7.searchplaceapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class PlaceUrlActivity extends AppCompatActivity {

    WebView web;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_url);

        //이 액티비티를 실행해준 택배기사님(Intent)에게 가지고 온 추가데이터를 얻어오기
        String place_url = getIntent().getStringExtra("place_url");

        web = findViewById(R.id.web);
        web.setWebViewClient(new WebViewClient());
        web.setWebChromeClient(new WebChromeClient());

        web.getSettings().setAllowFileAccess(true);
        web.getSettings().setJavaScriptEnabled(true);

        //웹뷰에게 장소정보 url을 읽어서 보여주도록
        web.loadUrl(place_url);
    }

    //백버튼 눌렀을때 발동하는 메소드

    @Override
    public void onBackPressed() {
        if (web.canGoBack()) web.goBack();
        else super.onBackPressed();
    }
}