package com.cloud7mu7.searchplaceapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.kakao.sdk.auth.model.OAuthToken;
import com.kakao.sdk.common.util.Utility;
import com.kakao.sdk.user.UserApiClient;
import com.kakao.sdk.user.model.User;
import com.nhn.android.naverlogin.OAuthLogin;
import com.nhn.android.naverlogin.OAuthLoginHandler;

import kotlin.Unit;
import kotlin.jvm.functions.Function2;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //카카오 키해시를 얻어오기
        String keyHash = Utility.INSTANCE.getKeyHash(this);
        Log.i("keyhash", keyHash);
    }

    public void clickGo(View view) {
        //MainActicity로 이동
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void clickSignUp(View view) {
        //회원가입 화면(액티비티)으로 이동
        startActivity(new Intent(this, SignUpActivity.class));
    }

    public void clickLoginEmail(View view) {
    }

    public void clickLoginKakao(View view) {

        //카카오계정 로그인 요청
        UserApiClient.getInstance().loginWithKakaoAccount(this, new Function2<OAuthToken, Throwable, Unit>() {
            @Override
            public Unit invoke(OAuthToken oAuthToken, Throwable throwable) {
                if (oAuthToken!=null){
                    Toast.makeText(LoginActivity.this, "로그인 성공", Toast.LENGTH_SHORT).show();

                    //로그인 사용자 정보 얻어오기
                    UserApiClient.getInstance().me(new Function2<User, Throwable, Unit>() {
                        @Override
                        public Unit invoke(User user, Throwable throwable) {
                            if(user!=null){
                                String userid = user.getId()+"";
                                String email = user.getKakaoAccount().getEmail();

                                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                                finish();
                            }
                            return null;
                        }
                    });
                }
                return null;
            }
        });

    }

    public void clickLoginGoogle(View view) {
    }

    public void clickLoginNaver(View view) {

        //주의사항 : 네이버로그인은 현재(2021.11.18) 타겟버전이 30버전까지 가능함

        //1. 네이버 로그인 인스턴스 초기화
        OAuthLogin oAuthLogin = OAuthLogin.getInstance();
        oAuthLogin.init(this, "xEmyUcjKm4SPB31owe0A", "pJjnhoiakG", "어디로가볼까");

        //2. 개발자 사이트에서 앱 등록 - 패키지명... 클라이언트ID, Secret 번호 받기

        //3. 로그인 버튼 구현 방법 2가지
        //3.1) 네이버로그인버튼 전용뷰 이용 - 이 버튼은 클릭이벤트 코드를 직접 작성하지 않아도 자동 로그인동작 가능
        //3.2) startOAuthLoginActvity() 메소드로 로그인 구현(커스텀 버튼 모양일때 사용)
        oAuthLogin.startOauthLoginActivity(this, new OAuthLoginHandler() {
            @Override
            public void run(boolean success) {
                //파라미터가 로그인 성공여부를 전달받음
                if(success){
                    Toast.makeText(LoginActivity.this, "로그인 성공", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}