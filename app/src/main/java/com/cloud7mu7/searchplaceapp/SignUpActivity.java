package com.cloud7mu7.searchplaceapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class SignUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_24);
    }

    //업버튼 클릭시에 액티비티를 종료

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()==android.R.id.home) onBackPressed();
        return super.onOptionsItemSelected(item);
    }

    public void clickSignUp(View view) {

        //Firebase Firestore DB에 이메일 사용자 정보 저장하기

        EditText etEmail = findViewById(R.id.et_email);
        EditText etPassword = findViewById(R.id.et_password);
        EditText etPasswordConfirm = findViewById(R.id.et_password_confirm);

        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();
        String passwordConfirm = etPasswordConfirm.getText().toString();

        if(!password.equals(passwordConfirm)){
            new AlertDialog.Builder(this).setMessage("패스워드 확인 문제가 있습니다. 다시 확인하여 입력해주세요.").show();
            etPasswordConfirm.requestFocus();
            etPasswordConfirm.selectAll();
            return;
        }

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        //저장한 값들(이메일, 비밀번호)을 HashMap으로 저장
        Map<String, String> user = new HashMap<>();
        user.put("email", email);
        user.put("password", password);

        db.collection("emailUsers").add(user).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                new AlertDialog.Builder(SignUpActivity.this).setMessage("회원가입 완료").show();
            }
        });



    }
}