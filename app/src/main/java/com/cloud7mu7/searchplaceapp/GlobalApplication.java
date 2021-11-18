package com.cloud7mu7.searchplaceapp;

import android.app.Application;

import com.kakao.sdk.common.KakaoSdk;

public class GlobalApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        //카카오 SDK 초기화설정
        KakaoSdk.init(this, "e1989f3f4b4a9093190b90b9d3a16278");
    }
}
