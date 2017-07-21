package com.lingling.linglingdating.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.lingling.linglingdating.R;

/**
 * Created by 令令 on 2017/7/13.
 * 这是登陆的界面，使用手机号登陆
 */
public class LogActivity extends BaseActivity {

    private Button into_log_phone_button;
    private EditText phone_log_phone_ed;
    private EditText pwd_log_phone_ed;
    private TextView pwdLog_phone_text;
    private Button yz_log_phone_button;
    private String phone_log;
    private String pwd_log;
    private String telRegex;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ling_log);
        //初始化界面
        initViews();
        initDatas();


    }

    private void initViews() {
        into_log_phone_button = (Button) findViewById(R.id.into_log_phone_button);
        phone_log_phone_ed = (EditText) findViewById(R.id.phone_log_phone_ed);
        pwd_log_phone_ed = (EditText) findViewById(R.id.pwd_log_phone_ed);
        pwdLog_phone_text = (TextView) findViewById(R.id.pwdLog_phone_text);
        yz_log_phone_button = (Button) findViewById(R.id.yz_log_phone_button);


   }
    //登陆
    private void initDatas() {
        //验证的表达式
        telRegex = "[1][358]\\d{9}";




        //点击进入判断
        into_log_phone_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phone_log = phone_log_phone_ed.getText().toString().trim();
                pwd_log = pwd_log_phone_ed.getText().toString().trim();
                Log.i("Mainlog=====",phone_log+phone_log.length());
                if (!TextUtils.isEmpty(phone_log)&& phone_log.length()==11){
                    Log.i("Mainlog=====",phone_log+phone_log.length());
                    boolean b = phone_log.matches(telRegex) ;
                    if (b==true){
                        //判断密码的正确
                        if(pwd_log!=null){
                            startActivity(new Intent(LogActivity.this,LingMainActivity.class));
                        }
                    }else{
                        Toast.makeText(LogActivity.this, "请输入正确的手机号码", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(LogActivity.this, "手机号或密码不能为空", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }



}

