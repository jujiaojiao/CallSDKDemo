package com.example.administrator.callsdkdemo;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText user;
    private EditText pwd;
    private Button register;
    private Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initListener();
    }
    private void initView(){
        user = ((EditText) findViewById(R.id.text_input_user));
        pwd = ((EditText) findViewById(R.id.text_input_pwd));
        register = ((Button) findViewById(R.id.register));
        login = ((Button) findViewById(R.id.login));
    }
    private void initListener(){
        login.setOnClickListener(this);
        register.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login://先拿用户名取密码，判断该用户是否注册
                            //再判断取出的密码是否和输入密码一致，若不一致不予进入
                break;
            case R.id.register:
                Intent intent = new Intent(this,RegisterActivity.class);
                startActivity(intent);
                break;
        }

    }


}
// eventHandler = new EventHandler() {
//public void afterEvent(int event, int result, Object data) {
//        if (data instanceof Throwable) {
//        Throwable throwable = (Throwable)data;
//        String msg = throwable.getMessage();
//        Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
//        } else {
//        if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) {
//        // 处理你自己的逻辑
//        }
//        }
//        }
//        };
//        // 注册监听器
//        SMSSDK.registerEventHandler(eventHandler);